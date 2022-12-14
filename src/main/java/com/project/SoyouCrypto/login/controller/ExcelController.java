package com.project.SoyouCrypto.login.controller;

import com.project.SoyouCrypto.login.repository.*;
import com.project.SoyouCrypto.login.service.DataService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.project.SoyouCrypto.login.model.*;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@RestController
public class ExcelController {

    private final ExcelDataRepository excelDataRepository;
    private final ExcelDataRepository2 excelDataRepository2;
    private final DataService dataService;
    private final CsvDataRepository csvDataRepository;
    private final MomentumDataAllRepository momentumDataAllRepository;
    private final MomentumData1YRepository momentumData1YRepository;
    private final MomentumData6MRepository momentumData6MRepository;
    private final MomentumData3MRepository momentumData3MRepository;
    private final StableDataAllRepository stableDataAllRepository;
    private final StableData1YRepository stableData1YRepository;
    private final StableData6MRepository stableData6MRepository;
    private final StableData3MRepository stableData3MRepository;

    @Autowired
    public ExcelController(
            ExcelDataRepository excelDataRepository, ExcelDataRepository2 excelDataRepository2,
            DataService dataService, CsvDataRepository csvDataRepository,
            MomentumDataAllRepository momentumDataAllRepository, MomentumData1YRepository momentumData1YRepository,
            MomentumData3MRepository momentumData3MRepository, MomentumData6MRepository momentumData6MRepository,
            StableDataAllRepository stableDataAllRepository, StableData1YRepository stableData1YRepository,
            StableData6MRepository stableData6MRepository, StableData3MRepository stableData3MRepository

    ) {
        this.excelDataRepository = excelDataRepository;
        this.dataService = dataService;
        this.excelDataRepository2 = excelDataRepository2;
        this.csvDataRepository = csvDataRepository;
        this.momentumDataAllRepository = momentumDataAllRepository;
        this.momentumData1YRepository = momentumData1YRepository;
        this.momentumData6MRepository = momentumData6MRepository;
        this.momentumData3MRepository = momentumData3MRepository;
        this.stableDataAllRepository = stableDataAllRepository;
        this.stableData1YRepository = stableData1YRepository;
        this.stableData6MRepository = stableData6MRepository;
        this.stableData3MRepository = stableData3MRepository;
    }

    @GetMapping("/momentum/graph")
    public Object getMomentumGraphData(@RequestParam String type) {
        System.out.println("????????? ????????? ????????? ?????????????");
        return dataService.getMomentumGraphData(type);
    }

    @GetMapping("/stable/graph")
    public Object getStableGraphData(@RequestParam String type) {
        return dataService.getStableGraphData(type);
    }


//    @GetMapping("/graphData3")
//    public List<MomentumDataAll> giveData3() {
//
//        return dataService.graphTries2();
//    }

    // ???????????? ?????????
    @PostMapping("/excel/read2")
    public String readExcel2(@RequestParam("file") MultipartFile file, Model model)
            throws IOException { // 2
        System.out.println("api ?????? ??????");

        List<ExcelData2> dataList = new ArrayList<>();

        String extension = FilenameUtils.getExtension(file.getOriginalFilename()); // 3

        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("??????????????? ????????? ????????????.");
        }

        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);

        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) { // 4

            Row row = worksheet.getRow(i);
            if(null == row) {
                continue;
            }
            ExcelData2 data = new ExcelData2();


            if(null != row.getCell(0)){
                data.setDate(row.getCell(0).getStringCellValue());}
            if(null != row.getCell(1)){
                data.setTerminal_price(row.getCell(1).getStringCellValue());}
            if(null != row.getCell(2)){
                data.setOpening_price(row.getCell(2).getStringCellValue());}
            if(null != row.getCell(3)){
                data.setTransactions(row.getCell(3).getStringCellValue());}
            if(null != row.getCell(4)){
                data.setChanges(row.getCell(4).getStringCellValue());}

            dataList.add(data);
            excelDataRepository2.save(data);
        }

        model.addAttribute("datas", dataList); // 5

        return "excelList";

    }

    // ????????? ?????? ???????????? ????????? ?????? (??????)
    @GetMapping("/momentum/index")
    public OutputIndex getPortfolioIndex(@RequestParam String type) {
        System.out.println("type ??? ??????????"+type);
        return dataService.getPortfolioIndex(type);
    }

    // csv momentumAll ?????????
    @PostMapping("csv/momentumDataAll")
    public String uploadMomentumAll(@RequestParam("file") MultipartFile file, Model model) {

        // validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {

            // parse CSV file to create a list of `User` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                CsvToBean<MomentumDataAll> csvToBeanAll = new CsvToBeanBuilder<MomentumDataAll>(reader)
                        .withType(MomentumDataAll.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                // convert `CsvToBean` object to list of users
                List<MomentumDataAll> dataAll = csvToBeanAll.parse();

                // TODO: save users in DB?
                momentumDataAllRepository.saveAll(dataAll);

                // save users list on model
                model.addAttribute("dataAll", dataAll);
                model.addAttribute("status", true);

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }
        System.out.println("csv api ??????2");
        return "file-upload-status";
    }

    // csv momentum 1Y ?????????
    @PostMapping("/csv/momentumData1Y")
    public String uploadMomentum1Y(@RequestParam("file") MultipartFile file, Model model) {

        // validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {

            // parse CSV file to create a list of `User` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                CsvToBean<MomentumData1Y> csvToBeanRaw = new CsvToBeanBuilder<MomentumData1Y>(reader)
                        .withType(MomentumData1Y.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                // convert `CsvToBean` object to list of users
                List<MomentumData1Y> data = csvToBeanRaw.parse();

                // TODO: save users in DB?
                momentumData1YRepository.saveAll(data);

                // save users list on model
                model.addAttribute("rawData", data);
                model.addAttribute("status", true);

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }
        System.out.println("csv api ??????");
        return "file-upload-status";
    }

    // csv momentum 6M ?????????
    @PostMapping("/csv/momentumData6M")
    public String uploadMomentum6M(@RequestParam("file") MultipartFile file, Model model) {

        // validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {

            // parse CSV file to create a list of `User` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                CsvToBean<MomentumData6M> csvToBeanRaw = new CsvToBeanBuilder<MomentumData6M>(reader)
                        .withType(MomentumData6M.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                // convert `CsvToBean` object to list of users
                List<MomentumData6M> data = csvToBeanRaw.parse();

                // TODO: save users in DB?
                momentumData6MRepository.saveAll(data);

                // save users list on model
                model.addAttribute("rawData", data);
                model.addAttribute("status", true);

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }
        System.out.println("csv api ??????");
        return "file-upload-status";
    }

    // csv momentum 3M ?????????
    @PostMapping("/csv/momentumData3M")
    public String uploadMomentum3M(@RequestParam("file") MultipartFile file, Model model) {

        // validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {

            // parse CSV file to create a list of `User` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                CsvToBean<MomentumData3M> csvToBeanRaw = new CsvToBeanBuilder<MomentumData3M>(reader)
                        .withType(MomentumData3M.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                // convert `CsvToBean` object to list of users
                List<MomentumData3M> data = csvToBeanRaw.parse();

                // TODO: save users in DB?
                momentumData3MRepository.saveAll(data);

                // save users list on model
                model.addAttribute("rawData", data);
                model.addAttribute("status", true);

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }
        System.out.println("csv api ??????");
        return "file-upload-status";
    }

    // ?????? ????????? ????????? ????????? ?????? index ??????
    @PutMapping("/excel/calcMomentumIndex")
    public void calcMomentumIndex() {

        dataService.momentumDataAllProcessing();
        dataService.momentumData1YProcessing();
        dataService.momentumData6MProcessing();
        dataService.momentumData3MProcessing();
    }

    // ?????? csv ????????? ??? ?????? ??? ????????? ????????? ??????
    @DeleteMapping("/momentum/dataTrim1Y")
    public void momentumDataTrim1Y() throws ParseException {
        dataService.momentumDataTrim1Y();
    }
    @DeleteMapping("/momentum/dataTrim6M")
    public void momentumDataTrim6M() throws ParseException {
        dataService.momentumDataTrim6M();
    }
    @DeleteMapping("/momentum/dataTrim3M")
    public void momentumDataTrim3M() throws ParseException {
        dataService.momentumDataTrim3M();
    }

    // Stable ?????????

    // csv stableAll ?????????
    @PostMapping("csv/stableDataAll")
    public String uploadStableAll(@RequestParam("file") MultipartFile file, Model model) {

        // validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {

            // parse CSV file to create a list of `User` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                CsvToBean<StableDataAll> csvToBeanAll = new CsvToBeanBuilder<StableDataAll>(reader)
                        .withType(StableDataAll.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                // convert `CsvToBean` object to list of users
                List<StableDataAll> dataAll = csvToBeanAll.parse();

                // TODO: save users in DB?
                stableDataAllRepository.saveAll(dataAll);

                // save users list on model
                model.addAttribute("dataAll", dataAll);
                model.addAttribute("status", true);

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }
        return "file-upload-status";
    }

    // csv stable 1Y ?????????
    @PostMapping("/csv/stableData1Y")
    public String uploadstable1Y(@RequestParam("file") MultipartFile file, Model model) {

        // validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {

            // parse CSV file to create a list of `User` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                CsvToBean<StableData1Y> csvToBeanRaw = new CsvToBeanBuilder<StableData1Y>(reader)
                        .withType(StableData1Y.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                // convert `CsvToBean` object to list of users
                List<StableData1Y> data = csvToBeanRaw.parse();

                // TODO: save users in DB?
                stableData1YRepository.saveAll(data);

                // save users list on model
                model.addAttribute("rawData", data);
                model.addAttribute("status", true);

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }
        System.out.println("csv api ??????");
        return "file-upload-status";
    }

    // csv stable 6M ?????????
    @PostMapping("/csv/stableData6M")
    public String uploadStable6M(@RequestParam("file") MultipartFile file, Model model) {

        // validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {

            // parse CSV file to create a list of `User` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                CsvToBean<StableData6M> csvToBeanRaw = new CsvToBeanBuilder<StableData6M>(reader)
                        .withType(StableData6M.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                // convert `CsvToBean` object to list of users
                List<StableData6M> data = csvToBeanRaw.parse();

                // TODO: save users in DB?
                stableData6MRepository.saveAll(data);

                // save users list on model
                model.addAttribute("rawData", data);
                model.addAttribute("status", true);

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }
        return "file-upload-status";
    }

    // csv stable 3M ?????????
    @PostMapping("/csv/stableData3M")
    public String uploadStable3M(@RequestParam("file") MultipartFile file, Model model) {

        // validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {

            // parse CSV file to create a list of `User` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                CsvToBean<StableData3M> csvToBeanRaw = new CsvToBeanBuilder<StableData3M>(reader)
                        .withType(StableData3M.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                // convert `CsvToBean` object to list of users
                List<StableData3M> data = csvToBeanRaw.parse();

                // TODO: save users in DB?
                stableData3MRepository.saveAll(data);

                // save users list on model
                model.addAttribute("rawData", data);
                model.addAttribute("status", true);

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }
        return "file-upload-status";
    }

    // ?????? ????????? ????????? ????????? ?????? index ??????
    @PutMapping("/excel/calcStableIndex")
    public void calcStableIndex() {

        dataService.stableDataAllProcessing();
        dataService.stableData1YProcessing();
        dataService.stableData6MProcessing();
        dataService.stableData3MProcessing();
    }

    // ?????? csv ????????? ??? ?????? ??? ????????? ????????? ??????
    @DeleteMapping("/stable/dataTrim1Y")
    public void dataTrim1Y() throws ParseException {
        dataService.stableDataTrim1Y();
    }
    @DeleteMapping("/stable/dataTrim6M")
    public void dataTrim6M() throws ParseException {
        dataService.stableDataTrim6M();
    }
    @DeleteMapping("/stable/dataTrim3M")
    public void dataTrim3M() throws ParseException {
        dataService.stableDataTrim3M();
    }
}
