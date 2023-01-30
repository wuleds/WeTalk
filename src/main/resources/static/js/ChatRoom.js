//选择图片
function clickImg(){
    const sendImg = document.getElementById("imgFile");
    sendImg.click();
}

//点击表情后向消息输入框添加一个表情
function clickFace(str) {
    textInputObj.value += str;
}

//清空聊天框内容
function reset() {
    textInputObj.value = null;
}