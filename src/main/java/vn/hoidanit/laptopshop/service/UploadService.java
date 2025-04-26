package vn.hoidanit.laptopshop.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {

    @Value("${app.image.upload.dir}") // Get value from application.properties
    private String imageUploadDir;

    private String uploadImageName = "";

    public String handleSaveUploadFile(MultipartFile file, String targetDir) {
        try {
            byte[] bytes = file.getBytes();

            File dir = new File(imageUploadDir + File.separator + targetDir);
            if (!dir.exists())
                dir.mkdirs();

            // Get upload image name
            uploadImageName = System.currentTimeMillis() + "-" + file.getOriginalFilename();

            // Create the file on server
            File serverFile = new File(dir.getAbsolutePath() + File.separator + uploadImageName);

            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return uploadImageName;
    }
}
