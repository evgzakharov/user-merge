# user-merge

The program supports the following methods to select input data:
1) chose file with user data:  
    ```
    -f <fileName>
    ```
    there `<fileName>` is path to file with input data

2) pass data in command line arguments: 
    ```
    -d '<dataLine1>' '<dataLine2>' 
    ```
    there `<dataLineN>` is a user data
    

Each line in file and `<dataLineN>` must be in next format:
```
<userName> -> <email1>, <email2>, ..
```
there `<userName>` is user name and `<emailN>` is a email list of user emails

## Build requirements:
- java 8

## Build

```sh
./gradlew shadowJar
```

## Example usage (after build)
- pass data through command line arguments:
```sh
java -jar build/libs/user-merge-1.0-SNAPSHOT-all.jar -d 'user1 -> xx@asd, asdf@sdf'
```
- pass data through file:
```sh
echo "user1 -> xx@asd, asdf@sdf\nuser2 -> xx@asd, asdfasdf@asdfs" > input_data.txt
java -jar build/libs/user-merge-1.0-SNAPSHOT-all.jar -f input_data.txt
```