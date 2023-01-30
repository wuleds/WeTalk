//汉江师范学院 数计学院 吴乐创建于2023/1/29 14:26:40

//发送消息
function sendMes()
{
    //获取输入框的值
    const mes = textInputObj.value;
    if(mes === '' || mes == null)
    {
        alert('没有输入消息');
    }else {
        //构造发给服务器消息
        const serverMesObj =
            {
                code:1,
                date:'',
                messageBody:{
                    toId:nowToId,
                    message:mes,
                    fromId:myId
                }
            };
        //转换为json格式
        const mesJson = JSON.stringify(serverMesObj);
        //发送消息
        websocket.send(mesJson);
    }
    //清空消息框
    reset();
}

//上传图片
let xhr;
function sendImg()
{
    const fileObj = document.getElementById("imgFile").files[0]; //js获取文件对象
    const url = "http://localhost/message/uploadImg";

    const form = new FormData();
    form.append("img", fileObj);

    xhr = new XMLHttpRequest();
    xhr.open("post", url, true);
    //post方式，url为服务器请求地址，true 该参数规定请求是否异步处理。
    xhr.onload = uploadComplete;
    //请求完成
    xhr.onerror = uploadFailed;
    //请求失败

    xhr.send(form);
    //开始上传，发送form数据
}

function uploadComplete()
{
    //获取返回的图片名，使用这个名字获取存在服务器上的图片
    const imgName = xhr.responseText;
    const serMesObj = {
        code:'2',
        date:'',
        messageBody:{
            toId:nowToId,
            message: imgName,
            fromId:myId
        }
    };
    const serverMessage = JSON.stringify(serMesObj);
    //通知服务器有图片消息，服务器会将图片名字 发给对话双方，前端拿图片
    websocket.send(serverMessage);
}
//上传失败
function uploadFailed() {
    alert("上传失败！");
}


//清空聊天框
function reset()
{
    const text = document.getElementById("textInput");
    text.value = null;
}