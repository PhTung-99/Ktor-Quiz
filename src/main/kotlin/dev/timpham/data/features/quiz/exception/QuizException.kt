package dev.timpham.data.features.quiz.exception

class QuizNotFoundException(): Exception("QUIZ_NOT_FOUND")

class InvalidAnswerRequestException(): Exception("INVALID_ANSWER")