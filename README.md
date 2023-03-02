
网络聊天室

一、引言
网络聊天室是一种非常流行的应用程序，它为用户提供了一个便捷的方式与其他人交流，分享信息和建立联系。随着互联网的发展和普及，越来越多的人开始使用网络聊天室，这使得网络聊天室的设计和开发变得尤为重要。
本次课程设计的目的是使用JavaWeb相关技术，开发一个基于Web的的网络聊天室，为用户提供一个良好的聊天体验和交流平台。该聊天室应具有简洁、友好的界面和强大的功能，包括用户注册、登录、私聊、群聊、在线状态等。同时，该聊天室还应该具有较高的可扩展性和可维护性，便于后期的升级和维护。
本次课程设计的作用是提高学生的JavaWeb开发能力，培养学生的软件开发思维和架构设计能力，同时让学生深入了解网络编程和实际项目开发的流程。通过本次课程设计，学生可以在实践中学习和掌握JavaWeb技术，提高自己的编码能力和项目管理能力，为将来的工作和学习打下坚实的基础。
二、任务需求
	1.需求分析
本次课程设计旨在开发一个基于JavaWeb的网络聊天室网站，实现用户之间的即时聊天功能。在开发过程中，需要满足以下功能需求：
（1）用户登录注册：用户可以进行账号注册，注册成功后可用该账号登录聊天室网站，如果已有账号则可以直接登录聊天室网站。
（2）私聊功能：用户可以查看在线用户列表，并实现用户之间的私聊功能。
（3）公共聊天室功能：用户可以加入公共聊天室，实现所有用户之间的即时聊天。
（4）消息发送：用户可以向好友或公共聊天室发送文本、图片、表情等消息。
（5）消息历史记录：系统会保存用户的聊天记录，用户重新进入聊天室也能看到聊天记录。
（6）日志记录：聊天室的日志会被记录在服务器的数据库中，比如上下线信息、聊天信息等。
2.完成要求：
（1）使用Java语言，以及使用SpringBoot、Mybatis等框架。
（2）数据库采用MySQL进行存储，使用Mybatis框架实现与数据库的交互。
（3）界面友好，操作简单，支持多种浏览器。
（4）实现即时通信功能，支持用户间的文本、图片、表情等消息传输。
（5）网页使用JavaScript与服务器连接，收发消息。
通过以上需求分析，我们可以清晰地了解到本次课程设计的目的是为了开发一个完整的Javaweb网络聊天室，实现用户之间的即时聊天功能，并且要求程序具有较高的安全性和稳定性。
3.方案论证
3.1 开发环境
（1）操作系统：Windows 10
（2）编码工具：IDEA 2022.3.2
（3）数据库系统：MySQL server 8
3.2 技术可行性研究
（1）在当前的限制条件和要求下，该系统的功能目标可以到达。
（2）利用现有的技术，例如Java、java Web、MySQL等技术，该程序的
功能可以实现。
（3）开发人员个数为一，开发人员的数量和质量可以满足该系统的程序
开发的所有需要。
（4）在规定的期限内，开发人员可以完成该系统的开发。
	4.用例图，如图2.1
图2.1 用例图
三、服务器代码设计
1.总体设计
1.1 系统结构设计，如图3.1
图3.1 系统结构图
	1.2 程序模块设计，如图3.2
图3.2 模块概要设计图
（1）config模块，SpringBoot的依赖，创建SpringBoot中需要的Bean。
	（2）controller模块，用于相应来自浏览器的请求。
	（3）dao模块，含有4个接口，用于查询数据库数据。
	（4）service模块，调用dao接口，获取并处理数据，返回给controller模块使用。
	（5）domain模块，模型模块，存放数据模型。
	（6）utils模块，存放工具类。
	1.3 数据库设计，如图3.3
图3.3 数据库概要设计图
（1）	private_chat_message_log数据库，用于存放私聊信息日志。
（2）	public_room_message_log数据库，用于存放公共聊天室信息日志。
（3）	user_login_log数据库，用于存放用户登录日志。
（4）	system_message_log数据库，用于存放系统消息日志。
（5）	user数据库，用于存放用户的账户和密码。
	2.详细设计
2.1 模块详细设计
2.1.1 config模块，如图3.4
图3.4 config模块详细设计图
2.1.2 controller模块，如图3.5
图3.5 controller模块详细设计图
（1）	WebSoketServer类，功能为与浏览器建立websocket协议的连接，从而进行全双工通信。
（2）	MessageController类，功能为接受、发送和保存用户发送的图片消息。
（3）	GetUserDataController类，功能为展示用户在线情况。
（4）	UserController类，功能为支持用户的登录和注册。
（5）	StartingController类，功能为展示网站首页。
（6）	GetViewController类，功能为跳转网页界面。
图3.6 dao模块详细设计图
2.1.3 dao模块，如图3.6
（1）	LogDao接口，用于记录日志。
（2）	SignupDao接口，用于注册。
（3）	UserDao接口，用于获取用户名。
（4）	LoginDao接口，用于登录验证。
2.1.4 domain模块，如图3.7
图3.7 domain模块详细设计图
2.1.5 service模块，如图3.8
（1）	LogService类，功能为调用dao模块接口保存日志。
（2）	SignupService类，功能为调用dao模块接口提交注册信息。
（3）	UserService类，功能为调用dao接口获取用户名字。
（4）	LoginService类，功能为调用dao接口验证登录信息。




图3.8 service模块详细设计图
2.1.6 utils模块，如图3.9
图3.9 utils模块详细设计图
（1）	MakeUtil类，是服务器消息工厂。
（2）	GetNowTime类，功能为返回当前系统时间。
（3）	MdUtil类，功能为返回MD5值。
2.2 数据库详细设计，如图3.10
2.2.1 表设计语句
create database wetalk;
（1）用户信息表，存储用户名、Id和密码
create table user
(
    userId  char(20) primary key,
    userName char(20) not null,
    password char(20) not null
);
（2）用户登录信息日志表，存储用户上线时间和下线时间
create table user_login_log
(
    id int auto_increment primary key ,
    userId char(20) not null,
    userName char(20) not null,
    loginDate char(20) not null,
    lineDate char(20)
);
（3）私聊信息日志表，存储用户间的私聊信息
create table private_chat_message_log
(
    id int auto_increment primary key ,
    formId char(20) not null,
    toId char(20) not null,
    message text not null,
    sendDate char(20) not null,
    code char not null
);
（4）公共聊天室信息日志表，存储用户在公共聊天室发的信息
create table public_room_message_log
(
    id int auto_increment primary key ,
    formId char(20) not null,
    message text not null,
    sendDate char(20) not null,
    code char not null
);
（5）系统消息日志表，存储系统消息
create table system_message_log
(
    id int auto_increment primary key,
    message text not null,
   		 sendDate char(20) not null
);
图3.10 数据库详细设计图
四、网页设计
1.登录界面设计，如图4.1
图4.1 登录界面图
图4.2 注册界面图
2.注册页面设计，如图4.2
3.聊天室页面设计，如图4.3
图4.3 聊天室页面图
（1）	点击“选图”按钮选择图片，然后点击“上传”按钮发送图
片。
（2）	点击右侧的表情可以在输入框中加入表情。
（3）	在输入框中输入文本，然后点击“发送”可以发送消息。
4.JavaScript代码设计

5.运行效果，如图4.4





五、课程设计总结
经过一个星期的努力，我完成了一个Javaweb网络聊天室的课程设计。在这个过程中，我学习了许多关于Java编程语言、Javaweb开发以及网络通信的知识和技能，并且掌握了SpringBoot和Mybatis框架。通过本次课程设计，我深入了解了软件开发的流程和方法，提高了自己的编程能力和实践能力。
在设计这个Javaweb网络聊天室的过程中，我明确了需求分析，包括登录、注册、在线聊天、私信等功能，并按照设计要求实现了这些功能。同时，我还通过使用Ajax技术以及Websocket网络协议实现了实时消息的推送，这为聊天室的实时性提供了支持。另外，我还为聊天室设计了简洁的界面，不华丽但实用，这提高了用户的体验。
在完成这个课程设计的过程中，我遇到了许多问题，如数据传输的安全问题、聊天记录的存储问题等等。通过解决这些问题，我学习到了更多的知识和技能，并提高了自己的解决问题的能力。
总之，这个课程设计是我在Java编程方面的一次挑战和锻炼。通过这个过程，我不仅获得了知识和技能，而且还提高了自己的实践能力和创新能力。这个课程设计对我的职业生涯有着积极的影响，我也期待在未来的学习和工作中能够继续不断提高自己。
