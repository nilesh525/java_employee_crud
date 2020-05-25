echo %path%

cd C:\Users\pa7nfa\Downloads\EmployeeCrud

setx -m JAVA_HOME "C:\Program Files\Java\jdk1.8.0_241"
setx -m JRE_HOME "C:\Program Files\Java\jdk1.8.0_241\jre"
setx -m JDK_HOME "%JAVA_HOME%"
setx -m M2_HOME "D:\software\apache-maven-3.6.3"
setx -m path "%path%;%JAVA_HOME%;%JRE_HOME%;%M2_HOME%\bin;"

echo %path%

pause