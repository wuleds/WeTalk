//汉江师范学院 数计学院 吴乐创建于2023/1/29 16:53:40

//展示聊天数据
function showMes(id,name)
{
    const mesTable = document.getElementById("mesTable");
    //转换当前聊天对象
    toId = id;
    toName = name;

    toNameObj.innerHTML = name;
    toIdObj.innerHTML = "(" + id + ")";

    const mes = sessionStorage.getItem(id);
    //不为空说明已经存了,展示消息
    if(mes != null)
    {
        mesTable.html(mes);
    }else {
        mesTable.html("");
    }

}

//展示在线人
function showOnline()
{
    const onlineUrl = "http://localhost/getUserData/getOnlineUserDao";


}


//点击更换聊天室
function replacementChat(id,name)
{
    toId = id;
    toName = name;

    reset();
    showMes(toId,toName);
}

