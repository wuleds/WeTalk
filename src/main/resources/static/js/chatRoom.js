//选择图片
function clickImg(){
    const sendImg = document.getElementById("imgFile");

    sendImg.click();
}

//点击表情
function clickFace(str)
{
    textInputObj.value += str;
}