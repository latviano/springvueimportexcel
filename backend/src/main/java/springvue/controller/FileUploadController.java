package springvue.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.text.DecimalFormat;


@RestController
public class FileUploadController {
    @Value("${spring.imagebyexcel.tempfilepath}")
    private String tempPath;
    @Value("${spring.imagebyexcel.targetpath}")
    private String targetPath;

    @CrossOrigin
    @PostMapping("/uploadExcelFile")
    public excelFileDesc uploadFile(Model model, @RequestParam("file") MultipartFile file) throws IOException {
        String fileLocation = saveFile(file);

        return parseFile(fileLocation);
    }

    public excelFileDesc parseFile(String path) throws IOException {
        excelFileDesc desc = new excelFileDesc();
        FileInputStream file = new FileInputStream(new File(path));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        int i = 1;
        for (Row row : sheet) {
            DecimalFormat df = new DecimalFormat("#");
            desc.addRow(i, df.format(row.getCell(0).getNumericCellValue()), df.format(row.getCell(1).getNumericCellValue()));
            i++;
        }
        return desc;
    }

    @CrossOrigin
    @PostMapping("/uploadFormData")
    public boolean uploadFormData(Model model, @RequestParam("excelData") String excelData, @RequestParam("file") MultipartFile file) throws IOException {
        String fileLocation = saveFile(file);
        ObjectMapper mapper = new ObjectMapper();
        try {
            excelFileDesc fileDesc = mapper.readValue(excelData, excelFileDesc.class);
            if (fileDesc.getList().size() > 0) {
                for (fileRowDesc row : fileDesc.getList()) {
                    processRow(row.getArticle(), row.getImgPlace(), fileLocation);
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private String saveFile(MultipartFile file) throws IOException {
        InputStream in = file.getInputStream();
        String fileLocation = tempPath + "/" + file.getOriginalFilename();
        FileOutputStream f = new FileOutputStream(fileLocation);
        int ch = 0;
        while ((ch = in.read()) != -1) {
            f.write(ch);
        }
        f.flush();
        f.close();

        return fileLocation;
    }

    private void processRow(String article, String requestPlace, String imageLocation) {
        String imgPlace = getFreePlace(article, requestPlace);
        File start = new File(imageLocation);

        File dest = new File(targetPath + "/" + article + "_" + imgPlace + "." + FilenameUtils.getExtension(imageLocation));
        if (!dest.exists()) {
            try {
                Files.copy(start.toPath(), dest.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getFreePlace(String article, String requestPlace) {
        idListAndMax list = getMaxPlace(article);
        int maxPlace = list.getMaxID();
        int myPlace = Integer.parseInt(requestPlace);
        myPlace = (myPlace != 0) ? myPlace : 1;
        if (myPlace > maxPlace) {
            myPlace = ++maxPlace;
        } else {
            moveImages(myPlace, maxPlace, article, list.getIdList());
        }
        return Integer.toString(myPlace);
    }

    private void moveImages(int minPlace, int maxPlace, String article, existingArticles artList) {
        int myPlace = maxPlace;
        while (myPlace >= minPlace) {
            String fileExt = artList.fileExtension.get(myPlace);
            File curFile = new File(targetPath + "/" + article + "_" + myPlace + "." + fileExt);
            File target = new File(targetPath + "/" + article + "_" + (myPlace + 1) + "." + fileExt);
            if (curFile.exists()) {
                try {
                    Files.move(curFile.toPath(), target.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            myPlace--;
        }
    }

    private idListAndMax getMaxPlace(String article) {
        existingArticles extArt = new existingArticles(article);
        int maxValue = 0;
        File[] files = new File(targetPath).listFiles((dir, name) -> name.startsWith(article));
        if (files.length > 0) {
            String curName;
            for (File f : files) {
                curName = f.getName();
                String[] s = (curName.split("_")[1]).split("\\.");
                int curValue = Integer.parseInt(s[0]);
                if (curValue > maxValue) {
                    maxValue = curValue;
                }
                extArt.addRow(curValue, s[1]);
            }
        }
        idListAndMax list = new idListAndMax(maxValue, extArt);
        return list;
    }
}

