package snippet;

public class Snippet {
	<dependencies>
			<!-- SPRING MVC -->	
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-web</artifactId>
			</dependency>
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-devtools</artifactId>
				<scope>runtime</scope>
				<optional>true</optional>
			</dependency>
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-tomcat</artifactId>
				<scope>provided</scope>
			</dependency>
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-test</artifactId>
				<scope>test</scope>
			</dependency>
			<!-- DISPLAYING JSP AND USING JSTL -->
	        <dependency>
	                <groupId>org.apache.tomcat.embed</groupId>
	                <artifactId>tomcat-embed-jasper</artifactId>
	        </dependency>
	        <dependency>
	                <groupId>javax.servlet</groupId>
	                <artifactId>jstl</artifactId>
	        </dependency>
		</dependencies>
}

