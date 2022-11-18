# 项目文档

- 页面区分
  - index页
  - login页
  - regist页
  - homepage页
  - 书籍查询页
  - 借阅申请页
  - 还书申请页

- 登录、注册页面的处理
  - html+servlet
  - user表调用以及回传信息

- homepage页处理
  - 接收loginservlet的登录信息
  - 回传user信息和调用userlog：user的信息

- 书籍查询页处理
  - 用户回传表单，后台回传表单信息
  - 调用book表，匹配，回传book表信息

- 借阅申请页处理
  - 用户提交表单，表单内容
    - 借阅的书籍id
    - 借阅时间
  - 后台
    - 查询书籍是否存在
    - 查询书籍是否在库
    - 设置借阅时间和归还时间
    - book表this.borrow+1
    - book表this.depot为0
    - booklog添加此条目
    - 返回booklog：thisbook信息：书id，借阅书名，用户名，借阅时间，归还时间

- 还书申请页处理
  - 回传此用户的booklog条目
    - booklog信息，列表排列
    - 头部表单，提交booklog：id信息返回归还信息
  - 后台
    - 接收booklog：id信息
    - book:thisbook.depot设置为1
    - book:this.receive+1
    - booklog:是否归还设置为1


- 数据库
  - user
    - 用于记录用户基本登录信息
    - 列表
      - 登录名：str
      - 密码：str
      - 邮箱：str
      - 电话：str
      - 性别：str
  - book
    - 用于登记书籍信息
    - 列表
      - 书籍id：int
      - 书籍名字：str
      - 借阅次数：int
      - 归还次数：int
      - 入库时间：date
      - 是否在库：bit
  - booklog
    - 记录书籍借阅信息，借出则条目+1，归还则销毁条目
    - 列表
      - 条目id：int
      - 书籍id：int
      - 书籍名：str
      - 借阅人/借阅id：int
      - 借阅时间：date
      - 归还时间：date
      - 是否归还：bit

- todo
  - 书籍查询
  - 借阅申请
  - 还书申请