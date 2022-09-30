javac -cp ../../lib/*;WEB-INF/lib/*;../../lib/tomcat/*; -d WEB-INF/classes src/com/model/* src/com/dao/* src/com/Singleton/* src/com/controller/* src/com/filegen/generator/* src/com/filegen/meta/* src/com/filegen/template/* src/com/filegen/util/pdf/* src/com/filegen/util/xlsx/* src/com/filegen/util/common/* src/service/auth/* src/service/user/* src/service/record/* src/service/googleauth/*
cd WEB-INF/classes
jar cf ../lib/bankapp.jar com/model/* com/dao/* com/Singleton/* com/controller/* com/filegen/generator/* com/filegen/meta/* com/filegen/template/* com/filegen/util/pdf/* com/filegen/util/xlsx/* com/filegen/util/common/* service/auth/* service/user/* service/record/* service/googleauth/*
cd ../../../../bin
app_ctl run