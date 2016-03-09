# TestS2Config

S2Config（というかjarのクラスパスで）でハマったので。。。

## プロジェクトの雛形作成

Doltengを使うと楽。

![Dolteng](https://github.com/mktktmr/TestS2Config/wiki/images/01.png)

Eclipse前提です。はい。

[Eclipseプラグインについて](https://www.seasar.org/updates.html)

## S2Configをダウンロード

Mavenを使うと楽。

詳しくはpom.xmlを参照。

## .diconファイルの設定

[ここ](http://tryerror.net/tryerror/wordpress/post-274)を参考に.diconファイルを設定していく（雑）。

今回Webプロジェクトじゃないので、上記サイトの
>次にweb.xmlに以下を記述

からの作業は必要ないです。

## jarファイルの作成

```
maven install
```
をすればjarは作成されるんだけど、依存ライブラリをコピーしたり、MANIFEST.MFにクラスパスを追加するのに、思考錯誤した。

詳しくはpom.xml参照（雑）。

ちなみにjarファイルはzipと同じ形式の圧縮ファイルなので、unzipなどで展開できます。

```
$ unzip -d TestS2Config TestS2Config-0.0.1.jar
$ ls -l TestS2Config
drwxr-xr-x  4 makoto  staff   136  3 10 01:55 META-INF
-rw-r--r--  1 makoto  staff   625  3  9 23:11 app.dicon
-rw-r--r--  1 makoto  staff   381  3  9 23:11 configCustomize.dicon
-rw-r--r--  1 makoto  staff   369  3  9 23:11 convention.dicon
-rw-r--r--  1 makoto  staff  1391  3  9 23:11 creator.dicon
-rw-r--r--  1 makoto  staff  2457  3  9 23:11 customizer.dicon
drwxr-xr-x  3 makoto  staff   102  3 10 01:55 data
-rw-r--r--  1 makoto  staff     2  3  9 23:11 env.txt
-rw-r--r--  1 makoto  staff     2  3  9 23:11 env_ut.txt
-rw-r--r--  1 makoto  staff    40  3  9 23:11 example.properties.bk
-rw-r--r--  1 makoto  staff  6232  3  9 23:11 jdbc.dicon
drwxr-xr-x  3 makoto  staff   102  3  9 23:12 jp
-rw-r--r--  1 makoto  staff   296  3  9 23:11 log4j.properties
-rw-r--r--  1 makoto  staff   393  3  9 23:11 s2container.dicon
```

せっかくなんで、MANIFEST.MFを覗いてみる。
```
$ vi TestS2Config/META-INF/MANIFEST.MF
```

こんな感じ。

```
Manifest-Version: 1.0
Archiver-Version: Plexus Archiver
Built-By: makoto
Class-Path: . lib/aopalliance-1.0.jar lib/commons-logging-1.1.jar lib/
 log4j-1.2.12.jar lib/geronimo-jta_1.1_spec-1.0.jar lib/h2-1.0.69.jar
 lib/ognl-2.6.9-patch-20090427.jar lib/poi-3.0-FINAL.jar lib/s2-extens
 ion-2.4.46.jar lib/s2-framework-2.4.46.jar lib/javassist-3.4.ga.jar l
 ib/junit-3.8.2.jar lib/s2config-core-1.0.3.jar lib/junit-addons-1.4.j
 ar lib/xercesImpl-2.6.2.jar lib/xmlParserAPIs-2.6.2.jar lib/s2-tiger-
 2.4.34.jar lib/easymock-2.2.jar lib/commons-lang-2.2.jar lib/s2config
 -extension-1.0.3.jar lib/geronimo-ejb_2.1_spec-1.1.jar lib/geronimo-e
 jb_3.0_spec-1.0.jar lib/geronimo-jpa_3.0_spec-1.0.jar lib/geronimo-an
 notation_1.0_spec-1.0.jar
Created-By: Apache Maven 3.2.1
Build-Jdk: 1.8.0_60
Main-Class: jp.co.example.Main
```

## 実行

そんなこんなでできあがた「TestS2Config-0.0.1.jar」を実行してみる。

前提としてTestS2Config-0.0.1.jarと同じディレクトリに「example.properties」があること。

```
$ java -jar TestS2Config-0.0.1.jar
2016-03-10 01:43:59,860 [main] INFO  org.seasar.framework.container.factory.SingletonS2ContainerFactory - s2-frameworkのバージョンは2.4.46です。
2016-03-10 01:43:59,861 [main] INFO  org.seasar.framework.container.factory.SingletonS2ContainerFactory - s2-extensionのバージョンは2.4.46です。
2016-03-10 01:43:59,861 [main] INFO  org.seasar.framework.container.factory.SingletonS2ContainerFactory - s2-tigerのバージョンは2.4.34です。
2016-03-10 01:44:00,063 [main] DEBUG org.seasar.framework.env.Env - 環境変数#Envにファイル(env.txt)から値(ct)が設定されました
〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜（省略）〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜
2016-03-10 01:44:00,525 [main] INFO  org.seasar.framework.container.factory.SingletonS2ContainerFactory - Running on [ENV]ct, [DEPLOY MODE]Hot Deploy
hoge
fuga
```
ちゃんとexample.propertiesの値を読み込めてることを確認。

## 参考

[How to add Class-Path to the manifest file with maven - stack overflow](http://stackoverflow.com/questions/23688063/how-to-add-class-path-to-the-manifest-file-with-maven)

[Apache Maven Archiver - Apache Maven Project](http://maven.apache.org/shared/maven-archiver/)

[Maven2 で JAR の Manifest ファイルを設定する方法をメモ](http://www.in-vitro.jp/blog/index.cgi/Maven/20060215_01.htm)

[プロジェクトが依存するjarファイルもそのまま出力する方法](http://www.h3.dion.ne.jp/~alpha-pz/misc2807.html)







