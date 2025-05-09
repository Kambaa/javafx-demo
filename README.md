# JDK 8+ Certificate Importer JavaFx Application

In enterprise company environments, you'll need to add the necessary certificates to the JDKs for connections to work.
Rather than remembering the `keytool` command, i've decided to write this mini JavaFx application to quickly add the certs to the truststore.

Just select the JDK(JAVA_HOME) folder and certificate and voila! cert is added to the JDK's trust store.

This tool works with every JDK version starting with JDK 8.

This was a nice JavaFx learning experience for me.

## Build Steps

Project needs JDK 21.

```
./mvnw clean javafx:jlink
```

```
jpackage  --dest target/ --runtime-image target/app --name "JDK8+ CertImporter" --module io.github.kambaa.javafxdemo/io.github.kambaa.javafxdemo.JavaFxApplication --type app-image --icon src/main/resources/app.ico 
```

add `--win-console` to the jpackage to display command line when running (to debug)
