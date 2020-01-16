package springvue.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springvue.domain.excelFileDesc;

import java.io.IOException;


@RestController
public class Controller {
    @Value("${spring.imagebyexcel.tempfilepath}")
    private String tempPath;
    @Value("${spring.imagebyexcel.targetpath}")
    private String targetPath;

    @CrossOrigin
    @PostMapping("/uploadExcelFile")
    public excelFileDesc uploadFile(Model model, @RequestParam("file") MultipartFile file) throws IOException {
        FileUploadController fileUploadController = new FileUploadController(tempPath, targetPath);
        String fileLocation = fileUploadController.saveFile(file);

        return fileUploadController.parseFile(fileLocation);
    }

    @CrossOrigin
    @PostMapping("/uploadFormData")
    public boolean uploadFormData(Model model, @RequestParam("excelData") String excelData, @RequestParam("file") MultipartFile file) throws IOException {
        FileUploadController fileUploadController = new FileUploadController(tempPath, targetPath);

        return fileUploadController.readExcelData(excelData,file);
    }
}
