# Challenge #2

Table of contents
1. [About The Project](#about-the-project)
    - [Adform's exam](#adforms-exam)
    - [Project description](#project-description)
2. [Getting Started](#getting-started)
3. [Usage](#usage)
4. [Troubleshooting](#troubleshooting)


## About the Project

### Adform's exam
This project is a part of a final exam after 3-months of Scala Academy at [Adform](https://site.adform.com/). The exam consists of 7 exercises. You can navigate to them in Exam TOC below.

<details>
  <summary>Exam TOC</summary>

1. [Challenge #1](https://github.com/rafalkac02/adform-exam-1)
2. [Challenge #2](https://github.com/rafalkac02/adform-exam-2)
3. [Challenge #3](https://github.com/rafalkac02/adform-exam-3)
4. [Challenge #4](https://github.com/rafalkac02/adform-exam-4)
5. [Challenge #5](https://github.com/rafalkac02/adform-exam-5)
6. [Challenge #6](https://github.com/rafalkac02/adform-exam-6)
7. [Challenge #7](https://github.com/rafalkac02/adform-exam-7)
</details>

### Project description
Challenge #2 is about Kafka Streams

- task description:
```
Write a Kafka Streams application that does the following transformations:
1. Words to upper case letters
2. Multiplies positive numbers by a number given on command line
3. Adds “negative number: “ prefix to negative numbers

Expected results
- All result groups should go to their respective topics.
- Input and output topics should be specified on the command line using scopt.
- Write tests.
- Don’t forget about README with proper structure
- Publish to a github repo
```

## Getting Started
The project is built with:
- Scala
- sbt
- kafka streams

## Usage
- Clone the repository to the chosen directory with `git clone https://github.com/rafalkac02/adform-exam-2` command.
- Run the application with `sbt run <args(0>` command in the same directory, where `args(0)` is the multiplier for positive numbers
- Test the application with `sbt test` command

The application reads from kafka topic named `input-topic` and writes to `output-topic`
- To produce messages, do the following in `kafka` directory on `ubuntu`:
1. run zookeeper
```     
bin/zookeeper-server-start.sh config/zookeeper.properties
```
2. start broker
```
bin/kafka-server-start.sh config/server.properties
```
3. create `my-input` topic
```
bin/kafka-topics.sh --create --topic input --bootstrap-server localhost:9092
```
4. start producer
```
bin/kafka-console-producer.sh --topic input --bootstrap-server localhost:9092
```

To see converted messages, run command
```
bin/kafka-console-consumer.sh --topic output-topic --bootstrap-server localhost:9092
```

## Troubleshooting
- Make sure that your project does not contain outdated project files using:
```
sbt clean
```
- Make sure that your environment variables are set properly, including `JAVA_HOME`
