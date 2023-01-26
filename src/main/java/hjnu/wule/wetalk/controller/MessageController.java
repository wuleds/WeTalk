//package hjnu.wule.wetalk.controller;
//
////汉江师范学院 数计学院 吴乐创建于2023/1/26 14:24:41
//
//import jakarta.websocket.Session;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//
///**
// * @作用 接收图片消息的控制器
// */
//@Controller
//@RequestMapping("/img")
//public class MessageController
//{
//    static {
//        System.out.println("MessageController Ready...");
//    }
//    @RequestMapping("/uploadImg")
//    @ResponseBody
//    public String uploadImg(MultipartFile image, Session session) throws IOException
//    {
//        // 检查文件内容是否为空
//        if (image.isEmpty()) {
//            return "no image input";
//        }
//
//        //原始文件名
//        String fileName = image.getOriginalFilename();
//
//        //保存图片，通过websocket给浏览器发消息，然后浏览器来取。
//        String name = saveImage(image);
//        String fileUri = null;
//
//        return fileUri;
//    }
//
//    @RequestMapping("/downloadImg/{imageName}")
//    public MultipartFile downloadImage(@PathVariable String imageName, Session session)
//    {
//
//    }
//
//    private String saveImage(MultipartFile file)
//    {
//
//        try {
//            String path = "/Image"+ file.getName();
//
//            File saveFile = new File();
//            file.transferTo(saveFile);
//
//            return path;
//        } catch (IllegalStateException | IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//}
