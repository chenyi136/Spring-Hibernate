Spring整合持久层框架无非就是配置对应的factory和DataSource，对照spring与hibernate的官方文档，官方文档有详细的配置说明。
本项目使用maven项目，H2数据库。在之前的spring整合mybatis中简单介绍了下。

----------

第一步：创建数据库表
----------

```
CREATE TABLE `student` (
  `id` varchar(255) NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `birthday` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `place` varchar(255) DEFAULT NULL,
  `dept` varchar(255) DEFAULT NULL,
  `clazz` varchar(255) DEFAULT NULL,
  `phoneNum` varchar(255) DEFAULT NULL,
  `idCard` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
```

第二步：创建maven项目，创建包结构
-------------------

![项目结构](http://img.blog.csdn.net/20170717205955461?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzMzMzE5OTU=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast) 

第三步：导包
------

1、	Spring-core
2、	Spring-comtext
3、	Spring-jdbc
4、	Spring-orm
5、	Hibernate-core
6、	Servlet-api
7、	Slf4j-simple
8、	Javassist
9、	mysql-connector-java
10、	h2
	使maven项目，编写pom.xml，管理包比较方便，添加以下依赖
```
<dependencies>

		<!-- Hibernate jar包 -->
		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-core</artifactId>
			<version>4.2.21.Final</version>
		</dependency>
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>servlet-api</artifactId>
			<version>3.0-alpha-1</version>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-simple</artifactId>
			<version>1.8.0-alpha2</version>
		</dependency>
		<dependency>
			<groupId>org.javassist</groupId>
			<artifactId>javassist</artifactId>
			<version>3.22.0-CR2</version>
		</dependency>

		<!-- Spring jar包 -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>4.1.6.RELEASE</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-core</artifactId>
			<version>4.1.6.RELEASE</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-jdbc</artifactId>
			<version>4.3.9.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-orm</artifactId>
			<version>4.3.9.RELEASE</version>
		</dependency>

		<!-- H2数据库jar包 -->
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<version>1.4.196</version>
		</dependency>

		<!-- JDBC jar包 -->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>8.0.7-dmr</version>
		</dependency>
	</dependencies>
```

添加了以上jar包以后，maven会把所有依赖的包自动添加进项目，所以这里的jar包比你添加的多。
 
![导入的jar包](http://img.blog.csdn.net/20170717210228998?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzMzMzE5OTU=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
第四步：编写代码
--------
Student.java（Student实体类，这里的注解使用属于hibernate基础在这里不再解释）

```
@Entity
public class Student {
	@Id
	private String id;
	private String nickname;
	private String name;
	private String sex;
	private String birthday;
	private String place;
	private String dept;
	private String clazz;
	private String phoneNum;
	private String idCard;
	
		sets and gets…
	}
```
StudentDao.java
```
public interface  StudentDao {
	public void add(Student student);
	public void delete(Student student);
	public void update(Student student);
	public Student find(String id);
	
}
```

StudentDaoImpl.java（这里只实现了find方法）

```
public class StudentDaoImpl implements StudentDao {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void add(Student student) {
	}

	public void delete(Student student) {
	}

	public void update(Student student) {
	}

	public Student find(String id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Student student = (Student)session.get(Student.class, id);
		session.getTransaction().commit();
		session.close();
		return student;
	}
}
```

Test.java（测试类）

```
public class Test {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
		StudentDao studentDao = (StudentDao)applicationContext.getBean("studentDao");
		Student student = studentDao.find("20141737");
		System.out.println(student);
	}
}
```

查看官网的文档，spring中有整合hibernate的配置说明
 ![spring中hibernate的目录](http://img.blog.csdn.net/20170717210330130?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzMzMzE5OTU=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
beans.xml
```
<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
    <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
    <property name="url" value="jdbc:h2:file:~/.h2/StudentManager1.0;AUTO_SERVER=TRUE"/>
    <property name="username" value="sa"/>
    <property name="password" value=""/>
</bean>

    <bean id="sessionFactory" class="org.springframework.orm.hibernate4.LocalSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
       <property name="packagesToScan">  
            <list>  
                <value>com.huagege.domain</value>  
            </list>  
        </property>  
        <property name="hibernateProperties">
            <value>
                hibernate.dialect=org.hibernate.dialect.H2Dialect
            </value>
        </property>
    </bean>
    
	<bean id="studentDao" class="com.huagege.dao.StudentDaoImpl">
		<property name="sessionFactory" ref="sessionFactory" />
	</bean>
	<bean id="student" class="com.huagege.domain.Student" />
```

第五步：运行
------

执行Text.java运行成功


----------


Spring中给的配置如下，其中三个地方需要修改
 
DataSource根据自己数据库的不同而填写，不多说，下面hibernate的方言根据自己的数据库填写不同的参数。
Spring给的例子是用xml文件映射实体类的，而本项目中使用的是注解方式，所以根据应修改成如下

```
<bean id="sessionFactory" class="org.springframework.orm.hibernate4.LocalSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
       <property name="packagesToScan">  
            <list>  
                <value>com.huagege.domain</value>  
            </list>  
        </property>  
        <property name="hibernateProperties">
            <value>
                hibernate.dialect=org.hibernate.dialect.H2Dialect
            </value>
        </property>
</bean>
```

packagesToScan对配置在其属性下的包进行扫描，具有@Entity的类将被扫描到。
 ![xml配置与注解配置对比](http://img.blog.csdn.net/20170717210406673?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzMzMzE5OTU=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
 


----------


新手学习，仅供参考
项目源码：https://github.com/huagegege/Spring-Hibernate.git
