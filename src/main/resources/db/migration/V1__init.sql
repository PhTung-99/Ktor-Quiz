CREATE TABLE IF NOT EXISTS roles
(
    id uuid NOT NULL PRIMARY KEY,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    description character varying(255) COLLATE pg_catalog."default" NOT NULL,
    is_active boolean NOT NULL,
    created_at_utc timestamp with time zone NOT NULL,
    is_deleted boolean NOT NULL
);

CREATE TABLE IF NOT EXISTS public."user"
(
    id uuid NOT NULL PRIMARY KEY,
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    email character varying(50) COLLATE pg_catalog."default" NOT NULL,
    password character varying(100) COLLATE pg_catalog."default" NOT NULL,
    avatar character varying(100) COLLATE pg_catalog."default",
    created_at_utc timestamp with time zone NOT NULL,
    is_deleted boolean NOT NULL
);

CREATE TABLE IF NOT EXISTS public.user_token
(
    id uuid NOT NULL PRIMARY KEY,
    user_id uuid NOT NULL,
    refresh_token character varying(500) COLLATE pg_catalog."default" NOT NULL,
    created_at_utc timestamp with time zone,
    is_deleted boolean NOT NULL,
    CONSTRAINT fk_user_token_user_id__id FOREIGN KEY (user_id)
        REFERENCES public."user" (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS public.user_roles
(
    id uuid NOT NULL PRIMARY KEY,
    user_id uuid NOT NULL,
    role_id uuid NOT NULL,
    created_at_utc timestamp with time zone,
    is_deleted boolean NOT NULL,
    CONSTRAINT fk_user_roles_role_id__id FOREIGN KEY (role_id)
        REFERENCES public.roles (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT,
    CONSTRAINT fk_user_roles_user_id__id FOREIGN KEY (user_id)
        REFERENCES public."user" (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS public.categories
(
    id uuid NOT NULL PRIMARY KEY,
    content character varying(500) COLLATE pg_catalog."default" NOT NULL,
    description character varying(500) COLLATE pg_catalog."default" NOT NULL,
--    icon character varying(500) COLLATE pg_catalog."default" NOT NULL,
    is_active boolean NOT NULL,
    created_at_utc timestamp with time zone,
    is_deleted boolean NOT NULL
);

CREATE TABLE IF NOT EXISTS public.quiz
(
    id uuid NOT NULL PRIMARY KEY,
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    description character varying(200) COLLATE pg_catalog."default" NOT NULL,
    type character varying(10) COLLATE pg_catalog."default" NOT NULL,
    is_active boolean NOT NULL,
    category uuid NOT NULL,
    created_at_utc timestamp with time zone,
    is_deleted boolean NOT NULL,
    CONSTRAINT fk_quiz_category__id FOREIGN KEY (category)
        REFERENCES public.categories (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS public.question
(
    id uuid NOT NULL PRIMARY KEY,
    name character varying(500) COLLATE pg_catalog."default" NOT NULL,
    highlight character varying(50) COLLATE pg_catalog."default",
    is_multiple_choice boolean NOT NULL,
    score integer NOT NULL,
    quiz uuid NOT NULL,
    created_at_utc timestamp with time zone,
    is_deleted boolean NOT NULL,
    CONSTRAINT fk_question_quiz__id FOREIGN KEY (quiz)
        REFERENCES public.quiz (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS public.answer
(
    id uuid NOT NULL PRIMARY KEY ,
    content character varying(500) COLLATE pg_catalog."default" NOT NULL,
    is_correct boolean NOT NULL,
    question uuid NOT NULL,
    created_at_utc timestamp with time zone,
    is_deleted boolean NOT NULL,
    CONSTRAINT fk_answer_question__id FOREIGN KEY (question)
        REFERENCES public.question (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS public.user_answer_history
(
    id uuid NOT NULL PRIMARY KEY,
    "user" uuid NOT NULL,
    quiz uuid NOT NULL,
    score integer NOT NULL,
    start_time timestamp with time zone NOT NULL,
    end_time timestamp with time zone NOT NULL,
    created_at_utc timestamp with time zone,
    is_deleted boolean NOT NULL,
    CONSTRAINT fk_user_answer_history_quiz__id FOREIGN KEY (quiz)
        REFERENCES public.quiz (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT,
    CONSTRAINT fk_user_answer_history_user__id FOREIGN KEY ("user")
        REFERENCES public."user" (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
);
