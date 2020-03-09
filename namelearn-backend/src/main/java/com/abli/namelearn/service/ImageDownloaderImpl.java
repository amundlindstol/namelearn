package com.abli.namelearn.service;

import com.abli.namelearn.domain.GetImage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@Service
public class ImageDownloaderImpl implements ImageDownloader {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpURLConnection con = (HttpURLConnection) new URL("https://wiki.intra.buypass.no/download/attachments/105449036/user-abli-ldap-image.png").openConnection();
        con.setRequestMethod("GET");
        con.addRequestProperty("Cookie","JSESSIONID=26CA5FA72CCB309D42D64643ABB78FA8");
        InputStream in = con.getInputStream();
        File downloadedFile = File.createTempFile("filenamePrefix", ".png");
        FileOutputStream out = new FileOutputStream(downloadedFile);
        byte[] buffer = new byte[1024];
        int len = in.read(buffer);
        while (len != -1) {
            out.write(buffer, 0, len);
            len = in.read(buffer);
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
        }
        in.close();
        out.close();
        System.out.println(downloadedFile.getAbsolutePath());
    }

    @Override
    public byte[] downloadImage(GetImage request) {
        HttpURLConnection con = null;
        InputStream in = null;
        try {
            con = (HttpURLConnection) new URL(request.getSrc()).openConnection();
            con.setRequestMethod("GET");
            con.addRequestProperty("Cookie", String.format("JSESSIONID=%s", request.getJSessionId()));
            in = con.getInputStream();
            return in.readAllBytes();
        } catch (IOException e) {
            log.error("Image download failed: {}", e.getMessage());
        }
        return new byte[0];
    }
}
