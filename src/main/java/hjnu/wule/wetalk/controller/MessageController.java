package hjnu.wule.wetalk.controller;

//汉江师范学院 数计学院 吴乐创建于2022/12/28 14:24:41

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

/**@作用 处理特殊消息的控制器*/
@Controller
@RequestMapping("/message")
public class MessageController
{
    static {
        System.out.println("MessageController Ready...");
    }

    /**上传图片，并保存在服务器本地，返回文件名
     * @return String
     * @param image 图片文件*/
    @RequestMapping("/uploadImg")
    @ResponseBody
    public String uploadImg(@RequestParam("img") MultipartFile image, HttpSession session)
    {
        System.out.println("MessageController.uploadImg start running");
        // 检查文件内容是否为空
        if (image.isEmpty()) {
            return "no image input";
        }

        //原始文件名
        String fileName = image.getOriginalFilename();

        System.out.println(fileName);

        String path = "D:/MyProgramProjects/WeTalk/src/main/resources/static/img/upload";

        //保存图片，通过websocket给浏览器发消息，然后浏览器来取。
        String name = saveImage(image,path,fileName);
        if(Objects.equals(name, "error"))
        {
            System.out.println("上传失败");
            return "error";
        }

        System.out.println(fileName+"上传成功");
        System.out.println(path+"/"+name);

        System.out.println("MessageController.uploadImg end run");

        return name;
    }

    /**根据名字下载图片,建立文件输出流 */
    @RequestMapping("/downloadImg/{imageName}")
    public void downloadImg (@PathVariable String imageName, HttpServletResponse response)
    {
        System.out.println("MessageController.downloadImg start running");

        String path = "D:/MyProgramProjects/WeTalk/src/main/resources/static/img/upload";
        String targetFile = path + "/" + imageName;

        File file = new File(targetFile);
        byte[] bytes = new byte[1024];

        OutputStream outputStream;
        FileInputStream fileInputStream;
        try
        {
            outputStream = response.getOutputStream();
            fileInputStream = new FileInputStream(file);

            while ((fileInputStream.read(bytes)) != -1)
            {
                //向网页的流写入数据
                outputStream.write(bytes);
                outputStream.flush();
            }
            //关闭流
            outputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("MessageController.downloadImg end run");
    }


    /**将文件保存到本地
     * @param file MultipartFile文件
     * @param path 要保存的路径
     * @param fileName 文件名*/
    private String saveImage(MultipartFile file,String path,String fileName)
    {
        String targetFile = path + "/" + fileName;
        File saveFile = new File(targetFile);
        try
        {
            if(!saveFile.exists())
            {
                if(!saveFile.createNewFile())
                {
                    return "error";
                }
            }
            file.transferTo(saveFile);
            if(!saveFile.exists())
            {
                return "error";
            }

        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }

        return saveFile.getName();
    }


}
