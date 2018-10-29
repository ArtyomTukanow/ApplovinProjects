@echo off
cd ane

copy /y "..\ASApplovinExtension\out\production\ASApplovinExtension\ASApplovinExtension.swc" ASApplovinExtension.swc

"%ProgramFiles(x86)%\7-zip\7z.exe" x ASApplovinExtension.swc -o"__temp" 
copy /y "__temp\library.swf" "default\library.swf"
copy /y "__temp\library.swf" "android\library.swf"


"%ProgramFiles(x86)%\7-zip\7z.exe" x "libs\*.jar" -o"__jarLibs" -y
"%ProgramFiles(x86)%\7-zip\7z.exe" d "android\library.jar"
cd __jarLibs
"%ProgramFiles(x86)%\7-zip\7z.exe" a "..\android\library.jar" "com"
cd ..

rmdir /s /q __temp
rmdir /s /q __jarLibs

adt -package -target ane applovinExtension.ane extension.xml -swc ASApplovinExtension.swc -platform Android-ARM -C android . -platform default -C default