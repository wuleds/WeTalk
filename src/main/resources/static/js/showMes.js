//汉江师范学院 数计学院 吴乐创建于2023/1/29 16:53:40

//展示聊天数据
function showMes(id,name)
{
    //转换当前聊天对象
    nowToId = id;
    nowToName = name;

    nowToNameObj.innerHTML = name;
    nowToIdObj.innerHTML = "(" + id + ")";

    const mes = localStorage.getItem(nowToId);
    //不为空说明已经存了,展示消息
    if(mes == null)
    {
        localStorage.setItem(nowToId,'');
        mesTable.innerHTML = '';
    }else {
        mesTable.innerHTML = mes;
    }
}

function showMesData(fromId,toId,date,mes,code){
    let sesMes;
    const fromName = onlineUserMap.get(fromId);
    if(toId === myId)//别人发给我的
    {//如果是私聊消息
        //取出私聊对象消息记录
        sesMes = localStorage.getItem(fromId);
        //拼接私聊对象消息
        if(code === '2')
        {//图片消息
            sesMes += '<tr><td class="mesCol"><div class="leftMesDiv">' + fromName + '('+fromId+')  '+ date + '</div><img src="http://localhost/message/downloadImg/'+mes+'" class="leftImg" alt="1"></td</tr>';
        }else {//文字消息
            sesMes += '<tr><td class="mesCol"><div class="leftMesDiv">'+ fromName + '('+fromId+')  '+ date +'</div><div class="leftMesBubbles"><div class="leftMes"></div><span>'+ mes +'</span></div></td></tr>';
        }
        //存储私聊对象消息
        localStorage.setItem(fromId,sesMes);

        if(fromId === nowToId)
            mesTable.innerHTML = sesMes;
    }else {//聊天室或我发的
        //取出与该聊天对象的消息记录
        sesMes = localStorage.getItem(toId);
        //拼接消息
        if (fromId === myId) {//如果是自己发的放右边
            if (code === '2') {
                sesMes += '<tr><td class="mesCol"><div class="rightMesDiv">' + myName + '(' + myId + ')  ' + date + '</div><img src="http://localhost/message/downloadImg/' + mes + '" class="rightImg" alt="1"></td</tr>';
            }else {
                sesMes += '<tr><td class="mesCol"><div class="rightMesDiv">'+ myName + '(' + myId + ')  ' + date + '</div><div class="rightMesBubbles"><div class="rightMes"></div><span style="color: #ffffff">'+ mes +'</span></div></td></tr>';
            }
        } else {//别人发的放左边
            if(code === '2')
            {
                sesMes += '<tr><td class="mesCol"><div class="leftMesDiv">' + fromName + '(' + fromId + ')  ' + date + '</div><img src="http://localhost/message/downloadImg/' + mes + '" class="leftImg" alt="1"></td</tr>';
            }else {
                sesMes += '<tr><td class="mesCol"><div class="leftMesDiv">'+ fromName + '('+fromId+')  '+ date +'</div><div class="leftMesBubbles"><div class="leftMes"></div><span>'+ mes +'</span></div></td></tr>';
            }
        }
        //存储消息
        localStorage.setItem(toId, sesMes);

        if (nowToId === toId) {
            mesTable.innerHTML = sesMes;
        }
    }
}

//展示在线用户
function showOnline()
{
    const onlineUrl = "http://localhost/getUserData/getOnlineUserData";
    const friendList = document.getElementById("friendList");
    const onlineCount = document.getElementById("onlineCount");
    let tr = '<tr><th><div style="font-size:20px;margin-left: 125px;width:80px;border-bottom-width: 2px;border-bottom-color: black;border-bottom-style: solid">好友列表</div></th>';
    const all = '<tr><td class="friendCol"><span class="friend" onclick="showMes(\'all\',\'聊天室\')">公共聊天室</span></td></tr>';
    tr = tr + all;
    friendList.innerHTML = tr;

    $.ajax(
        {
            type : "post",
            url : onlineUrl,
            async: false,
            dataType : "json",
            data:'',
            success:function(data)
            {
                let i = 0;
                $.each(data,function (userId,userName){
                    //展示在线用户
                    if(userId !== myId)
                    {
                        onlineUserMap.set(userId,userName);
                        tr += "<tr><td class=\"friendCol\"><span class=\"friend\" onclick=\"showMes('"+userId+"','"+userName+"')\">"+userName+" ("+userId+")"+"</span></td></tr>";
                        friendList.innerHTML = tr;
                        i++;
                    }
                })
                i++;
                onlineCount.innerHTML = "在线人数: "+i;
            }
        });
}

//展示系统消息
function showSysMes(date,mes){
    //获取消息记录
    let sysMes = sessionStorage.getItem("sysMesData");
    //添加消息
    sysMes += '<tr><td class="systemCol"><span>'+date+'&nbsp;&nbsp;</span><span>'+mes+'</span></td></tr>';
    //展示消息
    sysMesTableObj.innerHTML = sysMes;
    //存储消息
    sessionStorage.setItem("sysMesData",sysMes);
}

//更换聊天室
function replacementChat(id,name)
{
    nowToId = id;
    nowToName = name;

    reset();
    showMes(nowToId,nowToName);
}

