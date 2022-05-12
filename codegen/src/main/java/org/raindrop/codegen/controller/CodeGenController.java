package org.raindrop.codegen.controller;

import cn.hutool.core.util.ZipUtil;
import lombok.extern.slf4j.Slf4j;
import org.raindrop.codegen.con.Template;
import org.raindrop.codegen.dto.CreateTableDto;
import org.raindrop.codegen.utils.GenUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Slf4j
@RestController
public class CodeGenController {
    @Resource
    private GenUtils genUtils;

    @PostMapping("create-table")
    public void createTable(@RequestBody CreateTableDto tableDto, HttpServletRequest request, HttpServletResponse response) throws IOException {
        genUtils.createCode(tableDto);
        File file = ZipUtil.zip(Template.getRootPath());
        download(file, response);
        genUtils.delFile();
    }

    public byte[] fileToByte(File file) throws IOException{
        byte[] bytesArray = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(file);
        fis.read(bytesArray); //read file into bytes[]
        fis.close();
        return bytesArray;
    }

    public void download(File file, HttpServletResponse response) {
        try {
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
