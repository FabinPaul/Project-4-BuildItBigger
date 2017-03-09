package com.udacity.jokeds;

import java.util.Random;

public class JokesManager {

    private final String[] joke = {
            "Patient: Doctor! You\'ve got to help me! Nobody ever listens to me. No one ever pays any attention to what I have to say.\n" +
                    "Doctor: Next please! ",
            "Two boys were arguing when the teacher entered the room.\n" +
                    "The teacher says, \"Why are you arguing?\"\n" +
                    "\n" +
                    "One boy answers, \"We found a ten dollor bill and decided to give it to whoever tells the biggest lie.\"\n" +
                    "\n" +
                    "\"You should be ashamed of yourselves,\" said the teacher, \"When I was your age I didn\'t even know what a lie was.\"\n" +
                    "\n" +
                    "The boys gave the ten dollars to the teacher.",
            "Teacher: Why are you late? \n" +
                    "Student: There was a man who lost a hundred dollar bill. \n" +
                    "Teacher: That\'s nice. Were you helping him look for it? \n" +
                    "Student: No. I was standing on it. ",
            "Teacher: Did your father help your with your homework? \n" +
                    "Student: No, he did it all by himself.",
            "What is the difference between a snowman and a snowwoman?\n" +
                    "Snowballs.",
            "Teacher: Tell me a sentence that starts with an \"I\". \n" +
                    "Student: I is the....\n" +
                    "Teacher: Stop! Never put \'is\' after an \"I\". Always put \'am\' after an \"I\".\n" +
                    "Student: OK. I am the ninth letter of the alphabet. ",
            "My wife\'s cooking is so bad we usually pray after our food.",
            "PUPIL: \"Would you punish me for something I didn`t do?\" \n" +
                    "TEACHER:\" Of course not.\" \n" +
                    "PUPIL: \"Good, because I haven`t done my homework.\" ",
            "I\'d like to buy a new boomerang please. Also, can you tell me how to throw the old one away?",
            "Two elephants meet a totally naked guy. After a while one elephant says to the other: \"I really don\'t get how he can feed himself with that thing!\""
    };

    public String getJoke() {
        return joke[new Random().nextInt(10)];
    }
}
