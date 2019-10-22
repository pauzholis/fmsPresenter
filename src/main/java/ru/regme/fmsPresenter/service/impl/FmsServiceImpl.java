package ru.regme.fmsPresenter.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.hibernate.boot.archive.spi.ArchiveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.regme.fmsPresenter.database.dao.FmsRepository;
import ru.regme.fmsPresenter.database.entity.FederalMigrationService;
import ru.regme.fmsPresenter.model.FederalMigrationServiceDTO;
import ru.regme.fmsPresenter.model.ServerErrorException;
import ru.regme.fmsPresenter.service.FmsService;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Класс реализующий сервис работы с данными о ФМС
 */
@Service
public class FmsServiceImpl implements FmsService {

    private final String ARCHIVE_FILE_NAME = "fms_structure_10012018.zip";
    private FmsRepository fmsRepository;
    private static Logger log = LoggerFactory.getLogger(FmsServiceImpl.class);

    public FmsServiceImpl(FmsRepository fmsRepository) {
        this.fmsRepository = fmsRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveFms() {
        downloadZipFromUrl();
        try (ZipFile zipFile = new ZipFile(ARCHIVE_FILE_NAME)) {
            String uncompressedFileName = getUnzippedFileName(zipFile);
            if (uncompressedFileName != null) {
                List<String[]> allRows = getFmsLinesFromCsv(uncompressedFileName);
                for (String[] row : allRows) {
                    if (fmsRepository.getByNameAndCode(row[0], row[1]) == null) {
                        fmsRepository.save(new FederalMigrationService(row[0], row[1]));
                    }
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new ArchiveException("Ошибка обработки архива");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FederalMigrationServiceDTO> getAllFms() {
        List<FederalMigrationService> fmsList = fmsRepository.findAll();
        List<FederalMigrationServiceDTO> fmsDTOList = new ArrayList<>();
        for (FederalMigrationService fms : fmsList) {
            fmsDTOList.add(convertFmsToDTO(fms));
        }
        return fmsDTOList;
    }

    /**
     * Преобразовать объект из БД в модель для передачи на фронт
     *
     * @param fms Объект ФМС из БД
     * @return модель для передачи на фронт
     */
    private FederalMigrationServiceDTO convertFmsToDTO(FederalMigrationService fms) {
        return new FederalMigrationServiceDTO(fms.getName(), fms.getCode());
    }

    /**
     * Получить строки с данными о ФМС из CSV файла
     *
     * @param uncompressedFileName полное наименование файла из архива
     * @return строки с данными о ФМС
     * @throws IOException исключение обработки csv файла
     */
    private List<String[]> getFmsLinesFromCsv(String uncompressedFileName) throws IOException {
        CSVReaderBuilder reader = new CSVReaderBuilder(
                new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(uncompressedFileName), "Windows-1251"
                        )
                )
        );
        CSVReader csvReader = reader.withSkipLines(2).build();
        return csvReader.readAll();
    }

    /**
     * Извлечение содержимого из архива
     *
     * @param zipFile архив
     * @return полное имя извлеченного файла
     * @throws IOException исключение обработки архива
     */
    private String getUnzippedFileName(ZipFile zipFile) throws IOException {
        FileSystem fileSystem = FileSystems.getDefault();
        Enumeration<? extends ZipEntry> entries = zipFile.entries();

        String uncompressedFileName = null;
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            if (entry.isDirectory()) {
                Files.createDirectories(fileSystem.getPath(entry.getName()));
            } else {
                InputStream is = zipFile.getInputStream(entry);
                BufferedInputStream bis = new BufferedInputStream(is);
                uncompressedFileName = entry.getName();
                Path uncompressedFilePath = fileSystem.getPath(uncompressedFileName);
                File file = new File(uncompressedFileName);
                if (!file.exists()) {
                    Files.createFile(uncompressedFilePath);
                    FileOutputStream fileOutput = new FileOutputStream(uncompressedFileName);
                    while (bis.available() > 0) {
                        fileOutput.write(bis.read());
                    }
                    fileOutput.close();
                }
            }
        }
        return uncompressedFileName;
    }

    /**
     * Скачать архив по ссылке
     */
    private void downloadZipFromUrl() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            String archiveUrl = "http://webzato.com/fms/fms_structure_10012018.zip";
            byte[] archiveBytes = restTemplate.getForObject(archiveUrl, byte[].class);
            Files.write(Paths.get(ARCHIVE_FILE_NAME), archiveBytes);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new ArchiveException("Ошибка обработки архива");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FederalMigrationServiceDTO> getFmsByCode(String fmsCode) throws ServerErrorException {
        List<FederalMigrationService> fmsList = fmsRepository.getAllByCode(fmsCode);
        if (fmsList.isEmpty()) {
            throw new ServerErrorException("Не найдены ФМС с таким кодом");
        } else {
            List<FederalMigrationServiceDTO> fmsDtoList = new ArrayList<>();
            for (FederalMigrationService fms : fmsList) {
                fmsDtoList.add(new FederalMigrationServiceDTO(fms.getName(), fms.getCode()));
            }
            return fmsDtoList;
        }
    }
}
