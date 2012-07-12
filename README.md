Shrinkwrap Uberjar Archive Support
----------------------------------

This is Uberjar support for the excellent JBoss [Shrinkwrap](http://www.jboss.org/shrinkwrap/) library.

#### Add the following Maven repository to your pom.xml

	<repository>
		<id>cedricvidal-cloudbees-release</id>
		<name>cedricvidal-cloudbees-release</name>
		<url>https://repository-cedricvidal.forge.cloudbees.com/release/</url>
		<releases>
			<enabled>true</enabled>
		</releases>
		<snapshots>
			<enabled>false</enabled>
		</snapshots>
	</repository>

#### Add dependencies to your project

	<dependencies>
		<dependency>
			<groupId>biz.vidal.shrinkwrap</groupId>
			<artifactId>shrinkwrap-uberjar-api</artifactId>
			<version>0.0.3</version>
			<type>jar</type>
			<scope>compile</scope>
		</dependency>
		<dependency>
			<groupId>biz.vidal.shrinkwrap</groupId>
			<artifactId>shrinkwrap-uberjar-impl</artifactId>
			<version>0.0.3</version>
			<type>jar</type>
			<scope>compile</scope>
		</dependency>
	</dependencies>

#### Use it

For example, in order to create an uberjar for a [subethamail](http://code.google.com/p/subethasmtp/) SMTP server which would start on an available port for integration testing purposes, you could do the following:

Main.java

		JavaArchive bootJar = DependencyResolvers.use(MavenDependencyResolver.class).artifact("classworlds:classworlds-boot:1.0").resolveAs(JavaArchive.class).iterator().next();
		JavaArchive classworldsJar = DependencyResolvers.use(MavenDependencyResolver.class).artifact("classworlds:classworlds:1.1").resolveAs(JavaArchive.class).iterator().next();

		JavaArchive wiserBoot = create(JavaArchive.class, "wiser-boot.jar")
			.addClass(WiserBoot.class)
			.addClass(AvailablePortFinder.class)
			;

		File[] wiserLibs = DependencyResolvers.use(MavenDependencyResolver.class).artifact("org.subethamail:subethasmtp-wiser:1.2").resolveAsFiles();
		UberjarArchive uberJar = create(UberjarArchive.class)
			.merge(bootJar)
			.addAsLibrary(wiserBoot)
			.addAsLibraries(wiserLibs)
			.setMainClass(WiserBoot.class.getName())
			.setClassworlds(classworldsJar)
			.createConfiguration()
			;

		System.out.println(uberJar.toString(true));

WiserBoot:

	public static void main(String[] args) {
		Wiser wiser = new Wiser();
		int port = AvailablePortFinder.getNextAvailable(2500);
		wiser.setPort(port);
		wiser.start();
		System.out.println("Started SMTP server on " + port);
	}

[AvailablePortFinder](http://grepcode.com/file/repo1.maven.org/maven2/org.apache.camel/camel-test/2.9.1/org/apache/camel/test/AvailablePortFinder.java) is here taken from Camel test

#### Configuring classworlds assets

Shrinkwrap Uberjar support is built on the excellent [classworlds](http://classworlds.codehaus.org/) library, the one used to power maven complex classloading needs.

For now, the classworlds-boot artifact needs to manually be merged into the uberjar and the classworlds jar needs to manually be injected into the uberjar.

#### About uberjarring

The author of the original maven 1 uberjar plugin wrote an excellent explanation of the uberjarring process:
http://classworlds.codehaus.org/uberjar.html

Cedric Vidal
http://blog.proxiad.com
