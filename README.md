# (Spring boot + aws serverless + Graalvm) Demo App

## Prerequisites
1. Download and extract Graalvm. I have used graalvm for linux as I am using that so if you are working with linux then its good, otherwise you need to do operations using docker.
    ```
    wget https://download.oracle.com/graalvm/17/latest/graalvm-jdk-17_linux-x64_bin.tar.gz
    tar -xzf graalvm-jdk-17_linux-x64_bin.tar.gz
    ```
2. Download gcc
    ```
    sudo apt install gcc
    ```
3. Download zlib1g-dev
    ```
    sudo apt install zlib1g-dev
    ```
4. Also install sample-filters project by changing directory to /sample-filters and run following command
    ```
    mvn clean install
    ```

### Build and run native image locally.

1. Change into the project directory: `/pet-store-native`
2. Run the following to build a native image which will include all the necessary dependencies to build the application and zip then with bootstrap file. It will take around 3-4 minutes. It may potentially give you ran out of memory error, just run it.
    ```
    PATH=/path/to/your/graal/graalvm-jdk-17.0.10+11.1/bin:$PATH JAVA_HOME=/path/to/your/graal/graalvm-jdk-17.0.10+11.1 ./mvnw clean -Pnative package -DskipTests
    ```
3. Build the application within the previously created build image
    ```
    sam build
    ```
4. After the build finishes, you need to deploy the function:
    ```
    sam local start-api
    ```
5. To test it send request to `http://127.0.0.1:3000/hello` and check trace to see filters execution.