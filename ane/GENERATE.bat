@echo off

copy /y "..\ASApplovinExtension\out\production\ASApplovinExtension\ASApplovinExtension.swc" ASApplovinExtension.swc

"%ProgramFiles(x86)%\7-zip\7z.exe" x ASApplovinExtension.swc -o"__temp"

copy /y "__temp\library.swf" "default\library.swf"
copy /y "__temp\library.swf" "android\library.swf"

rmdir /s /q __temp

adt -package -target ane applovinExtension.ane extension.xml -swc ASApplovinExtension.swc -platform Android-ARM -C android . -platform default -C default