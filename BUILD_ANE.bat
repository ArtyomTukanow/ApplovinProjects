@echo off
cd ane

@echo Копируем ASApplovinExtension.swc из проекта и получаем из него library.swf


copy /y "..\ASApplovinExtension\out\production\ASApplovinExtension\ASApplovinExtension.swc" ASApplovinExtension.swc

"%ProgramFiles(x86)%\7-zip\7z.exe" x ASApplovinExtension.swc -o"__temp" 
copy /y "__temp\library.swf" "default\library.swf"
copy /y "__temp\library.swf" "android\library.swf"


@echo Удаляем временные файлы

rmdir /s /q __temp

@echo Запускаем скрипт на создание applovinExtension.ane

adt -package -target ane applovinExtension.ane extension.xml -swc ASApplovinExtension.swc -platform Android-ARM -platformoptions platform.xml -C android . -platform default -C default