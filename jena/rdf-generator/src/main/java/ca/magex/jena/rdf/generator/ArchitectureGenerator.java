package ca.magex.jena.rdf.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import ca.magex.jena.rdf.model.EntityRef;
import ca.magex.jena.rdf.model.Predicate;
import ca.magex.jena.rdf.model.Property;
import ca.magex.jena.rdf.model.Subject;
import ca.magex.jena.rdf.model.TextValue;
import ca.magex.jena.rdf.model.Triple;
import ca.magex.jena.rdf.model.Value;

public class ArchitectureGenerator {
	
	private OutputStream os;

	public void run(OutputStream os) throws IOException {
		this.os = os;
		
		EntityRef system = subject("system", "domain", "system", "System");
		EntityRef computer = subject("system", "domain", "computer", "Computers");
		EntityRef softwareGenre = subject("system", "type", "computer:software_genre", "Software Genre");
		emit(softwareGenre, "system.type.domain", computer);

		EntityRef application = subject("computer", "software_genre", "application", "Application");
			EntityRef database = subject("computer", "software_genre", "database", "Data Base");
			EntityRef batchApplication = subject("computer", "software_genre", "batchApplication", "Batch Application");
			EntityRef webApplication = subject("computer", "software_genre", "webApplication", "Web Application");
			EntityRef desktopAppliaction = subject("computer", "software_genre", "desktopAppliaction", "Desktop Application");
			EntityRef webServer = subject("computer", "software_genre", "webServer", "Web Server");
			
		EntityRef computerSecurity = subject("computer", "software_genre", "computerSecurity", "Computer Security");
			EntityRef authentication = subject("computer", "software_genre", "authentication", "Authentication");
			
		EntityRef operatingSystem = subject("computer", "software_genre", "operatingSystem", "Operating System");
			EntityRef backupSoftware = subject("computer", "software_genre", "backupSoftware", "Backup Software");
			EntityRef fileManagement = subject("computer", "software_genre", "fileManagement", "File manager");
			EntityRef fileArchiving = subject("computer", "software_genre", "fileArchiving", "File Archiving");
			EntityRef fileSharing = subject("computer", "software_genre", "fileSharing", "File sharing");
			EntityRef emulation = subject("computer", "software_genre", "emulation", "Emulation");
			EntityRef virtualization = subject("computer", "software_genre", "virtualization", "Virtualization");

		EntityRef productivity = subject("computer", "software_genre", "productivity", "Productivity");
			EntityRef automation = subject("computer", "software_genre", "automation", "Automation");
			EntityRef naturalLanguageProcessing = subject("computer", "software_genre", "naturalLanguageProcessing", "Natural Language Processing");
			EntityRef textEditor = subject("computer", "software_genre", "textEditor", "Text Editor");
			
		EntityRef programmingLanguage = subject("computer", "software_genre", "programmingLanguage", "Programming Language");

		EntityRef searchEngine = subject("computer", "software_genre", "searchEngine", "Search Engine");
		EntityRef wiki = subject("computer", "software_genre", "wiki", "Wiki");
		EntityRef issueManagement = subject("computer", "software_genre", "issueManagement", "Issue Management System");
		EntityRef sourceControlManagement = subject("computer", "software_genre", "sourceControlManagement", "Source Control Management");
		EntityRef webBrowser = subject("computer", "software_genre", "webBrowser", "Web Browser");
		EntityRef javaee = subject("computer", "software_genre", "javaee", "Java Enterprise Edition");
			EntityRef applicationServer = subject("computer", "software_genre", "applicationServer", "Application Server");
			EntityRef businessServer = subject("computer", "software_genre", "businessServer", "Business Service");
			EntityRef applicationUtilities = subject("computer", "software_genre", "applicationUtilities", "Application Utilities");
			EntityRef javaFramework = subject("computer", "software_genre", "javaFramework", "Java Framework");
			EntityRef domainMode = subject("computer", "software_genre", "domainMode", "Domain Model");
		EntityRef dataAccessLayer = subject("computer", "software_genre", "dataAccessLayer", "Data Access Layer");
		EntityRef proxyServer = subject("computer", "software_genre", "proxyServer", "Proxy Server");
		EntityRef deploymentSoftware = subject("computer", "software_genre", "deploymentSoftware", "Deployment Software");
		EntityRef buildSoftware = subject("computer", "software_genre", "buildSoftware", "Build Software");
		EntityRef htmlEditor = subject("computer", "software_genre", "htmlEditor", "HTML Editor");
		EntityRef crm = subject("computer", "software_genre", "crm", "Contact Relationship Management");
		EntityRef validation = subject("computer", "software_genre", "validation", "Validation Software");
			EntityRef accessibilityValidation = subject("computer", "software_genre", "accessibilityValidation", "Accessibility Validation");
			EntityRef usabilityValidation = subject("computer", "software_genre", "usabilityValidation", "Usability Validation");
			EntityRef securityValidation = subject("computer", "software_genre", "securityValidation", "Security Validation");
			EntityRef functionalValidation = subject("computer", "software_genre", "functionalValidation", "Functionalitiy Validation");
			EntityRef dataValidation = subject("computer", "software_genre", "dataValidation", "Data Validation");
		EntityRef bi = subject("computer", "software_genre", "bi", "Business Intelligence");
		EntityRef passwordManagement = subject("computer", "software_genre", "passwordManagement", "Password Manager");
		EntityRef etl = subject("computer", "software_genre", "etl", "Extract Transform Load");
		EntityRef buildAutomation = subject("computer", "software_genre", "buildAutomation", "Build Automation");
		EntityRef softwareDesign = subject("computer", "software_genre", "softwareDesign", "Software Design");
		EntityRef testingFramework = subject("computer", "software_genre", "testingFramework", "Testing Framework");
			EntityRef mobileTesting = subject("computer", "software_genre", "mobileTesting", "Mobile Testing");
			EntityRef networkTesting = subject("computer", "software_genre", "networkTesting", "Network Testing");
			EntityRef accessibilityTesting = subject("computer", "software_genre", "accessibilityTesting", "Accessibility Testing");
		EntityRef logManagement = subject("computer", "software_genre", "logManagement", "Log management");
		EntityRef cots = subject("computer", "software_genre", "cots", "Commercial off the Shelf");
		EntityRef oss = subject("computer", "software_genre", "oss", "Free and Open Source");
		EntityRef dnsServer = subject("computer", "software_genre", "dnsServer", "Dns Server");
		EntityRef contentRepository = subject("computer", "software_genre", "contentRepository", "Content Repository");
		EntityRef wysiwyg = subject("computer", "software_genre", "wysiwyg", "WYSIWYG HTML Editor");
		EntityRef uml = subject("computer", "software_genre", "uml", "UML Tools");
		EntityRef protocol = subject("computer", "software_genre", "protocol", "Network Protocol");
		EntityRef releaseManagement = subject("computer", "software_genre", "releaseManagement", "Release and Deploy Software");
		EntityRef reporting = subject("computer", "software_genre", "reporting", "Reporting Software");
		EntityRef dataCapture = subject("computer", "software_genre", "dataCapture", "Data Capture");
		
		EntityRef framework = subject("computer", "software_genre", "framework", "Framework", "Software which sits behine the scenes");
		EntityRef collaboritive = subject("computer", "software_genre", "collaboritive", "Collaboritive", "Software which allows collaborative modification...");
		EntityRef browser = subject("computer", "software_genre", "browser", "Browser", "A web browser so no extra softwrae needs to be installed");
		EntityRef email = subject("computer", "software_genre", "email", "Email", "Email to send other people messages.");
		EntityRef cms = subject("computer", "software_genre", "cms", "Content Management System");

		EntityRef java = subject("computer", "application", "java", "Java", "Java is a general-purpose computer programming language.");
		emit(java, "computer.application.software_genre", framework);

		EntityRef java6 = subject("computer", "software", "java6", "Java 1.6", "Version 1.6 or 6 of Java");
		emit(java6, "computer.software.application", java);
		emit(java6, "computer.version.fullVersion", "1.6");
		emit(java6, "computer.version.majorVersion", "1");
		emit(java6, "computer.version.minorVersion", "6");
		
		EntityRef linux = subject("computer", "application", "linux", "Linux", "A popular operating system built by Linus");
		emit(linux, "computer.application.software_genre", operatingSystem);

		EntityRef windows = subject("computer", "application", "windows", "Microsoft Windows", "A popular operating system built by Microsoft");
		emit(windows, "computer.application.software_genre", operatingSystem);

		EntityRef chrome = subject("computer", "application", "chrome", "Chrome", "Google's web browser");
		emit(chrome, "computer.application.software_genre", browser);
		
		EntityRef ie = subject("computer", "application", "ie", "Internet Explorer", "Microsoft's web browser");
		emit(ie, "computer.application.software_genre", browser);
		
		EntityRef ie9 = subject("computer", "software", "ie9", "Internet Explorer 9", "Version 9 of IE");
		emit(ie9, "computer.software.appliation", ie);
		emit(ie9, "computer.version.fullVersion", "9.0");
		emit(ie9, "computer.version.majorVersion", "9");
		emit(ie9, "computer.version.minorVersion", "0");
		
		EntityRef firefox = subject("computer", "application", "firefox", "Firefox", "Mozilla's web browser");
		emit(firefox, "computer.software.software_genre", browser);
		
		EntityRef confluence = subject("computer", "application", "confluence", "Confluence", "A wiki built by atlassian");
		emit(confluence, "computer.software.software_genre", wiki);
		emit(confluence, "computer.software.software_genre", collaboritive);
		emit(confluence, "computer.software.languages_used", java);
		emit(confluence, "computer.software.computer_os", windows);
		emit(confluence, "computer.software.computer_os", linux);
		emit(confluence, "computer.software.client", ie9);
		emit(confluence, "computer.software.client", chrome);
		emit(confluence, "computer.software.client", firefox);
		
		EntityRef userpass = subject("computer", "authentication_method", "userpass", "Username and Password", "Both a username and password are required");
		EntityRef localDatabase = subject("computer", "authentication_store", "localDatabase", "Application Database", "A local database only used by this application");
		
		emit(confluence, "computer.software.authentication_method", userpass);
		emit(confluence, "computer.software.authentication_store", localDatabase);
		
		EntityRef database3309 = subject("computer", "protocol_used", "database3309", "Database Port", "The standard database port 3309");
		EntityRef web8080 = subject("computer", "protocol_used", "web8080", "Java Web Port", "The standard java web port 8080");
		
		emit(confluence, "computer.software.protocol_used", database3309);
		emit(confluence, "computer.software.protocol_used", web8080);
		
		EntityRef outlook = subject("computer", "application", "outlook", "Outlook", "Microsofts email client");
		emit(outlook, "computer.software.software_genre", email);
		
		EntityRef gmail = subject("computer", "application", "gmail", "Google Mail", "Googles email client");
		emit(gmail, "computer.software.software_genre", email);

		EntityRef pr = subject("computer", "environment", "pr", "Production", "Production servers that the end users perform their business on");
		
		EntityRef emailContainer = subject("computer", "container", "exchange", "Outlook Exchange", "Exchange Server");
		emit(emailContainer, "computer.container.software_genre", email);
		
		EntityRef emailServerPr = subject("computer", "server", emailContainer + ":" + pr, "Production Exchange Server", "Outlook email server for corporate servers");
		emit(emailServerPr, "computer.server.container", emailContainer);
		emit(emailServerPr, "computer.server.environment", pr);
		
		EntityRef drupal = subject("computer", "application", "drupal", "Drupal");
		emit(drupal, "computer.application.software_genre", cms);

		EntityRef wordpress = subject("computer", "application", "wordpress", "Wordpress");
		emit(wordpress, "computer.application.software_genre", cms);
	}

	private EntityRef subject(String domain, String type, String key, String name, String description) throws IOException {
		EntityRef subject = new EntityRef(domain, type, key);
		emit(subject, "common.system.ref", "ref://" + domain + "/" + type + "/" + key);
		emit(subject, "common.system.domain", new EntityRef("system", "domain", domain));
		emit(subject, "common.system.type", new EntityRef("system", "type", type));
		emit(subject, "common.system.key", key);
		emit(subject, "common.topic.name", name);
		emit(subject, "common.topic.description", description);
		return subject;
	}
	
	private EntityRef subject(String domain, String type, String key, String name) throws IOException {
		EntityRef subject = new EntityRef(domain, type, key);
		emit(subject, "common.system.ref", "ref://" + domain + "/" + type + "/" + key);
		emit(subject, "common.system.domain", new EntityRef("system", "domain", domain));
		emit(subject, "common.system.type", new EntityRef("system", "type", domain + ":" + type));
		emit(subject, "common.system.key", key);
		emit(subject, "common.topic.name", name);
		return subject;
	}
	
	private void emit(Subject subject, String predicate, String value) throws IOException {
		emit(subject, predicate, new TextValue(value));
	}
	
	private void emit(Subject subject, String predicate, Value value) throws IOException {
		String[] parts = predicate.split("\\.");
		emit(subject, new Property(parts[0], parts[1], parts[2]), value);
	}
	
	private void emit(Subject subject, Predicate predicate, Value value) throws IOException {
		Triple triple = new Triple(subject, predicate, value);
		os.write(triple.toString().getBytes());
		os.write("\n".getBytes());
	}

	public static void main(String[] args) throws Exception {
		File file = new File("target/architecture.ttl");
		generate(file);
		//HtmlGenerator.print(HtmlGenerator.buildModel(file));
		//HtmlGenerator.html(HtmlGenerator.buildModel(file), "target/html");
	}
	
	public static void generate(File outputFile) throws Exception {
		FileOutputStream fos = new FileOutputStream(outputFile);
		//OutputStream os = new GZIPOutputStream(fos);
		
		fos.write("@prefix entity: <http://magex.ca/data/system/entity/> .\n".getBytes());
		fos.write("@prefix prop: <http://magex.ca/data/system/property/> .\n".getBytes());
		fos.write("\n".getBytes());
		
		new ArchitectureGenerator().run(fos);
		new ArchitectureGenerator().run(System.out);

		//os.close();
		fos.close();
	}
	
}
