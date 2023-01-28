//展示表情
function showFace()
{
    const faceTable = document.getElementById('faceTable');

    if(faceTable.style.display === 'none')
    {
        faceTable.style.display = 'block';
    }else {
        faceTable.style.display = 'none';
    }
}

//选择图片
function sendImg(){
    const sendImg = document.getElementById("sendImg");

    sendImg.click();
}