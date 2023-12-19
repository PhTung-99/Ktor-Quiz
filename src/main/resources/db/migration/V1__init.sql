CREATE TABLE IF NOT EXISTS public."user"
(
    id uuid NOT NULL PRIMARY KEY,
    name character varying(50) NOT NULL,
    email character varying(50) NOT NULL UNIQUE,
    password character varying(100) NOT NULL,
    avatar character varying(100),
    role character varying(10) NOT NULL,
    created_at_utc timestamp with time zone NOT NULL,
    is_deleted boolean NOT NULL
);

INSERT INTO public."user"(
	id, name, email, password, role, created_at_utc, is_deleted)
	VALUES (gen_random_uuid(), 'admin', 'admin@admin.com', '$2a$10$m9hGHHOy5BtcRmg.iYXpt.SpKe/.8DNzy1P3iKe30bCfmP2Vt4.OO', 'ADMIN', '2023-12-19 06:43:10.281392+00', false);


CREATE TABLE IF NOT EXISTS public.user_token
(
    id uuid NOT NULL PRIMARY KEY,
    "user" uuid NOT NULL,
    refresh_token character varying(500) NOT NULL,
    created_at_utc timestamp with time zone,
    is_deleted boolean NOT NULL,
    CONSTRAINT fk_user_token_user_id__id FOREIGN KEY ("user")
        REFERENCES public."user" (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS public.categories
(
    id uuid NOT NULL PRIMARY KEY,
    content character varying(500) NOT NULL,
    description character varying(500) NOT NULL,
--    icon character varying(500)  NOT NULL,
    is_active boolean NOT NULL,
    created_at_utc timestamp with time zone,
    is_deleted boolean NOT NULL
);

CREATE TABLE IF NOT EXISTS public.quiz
(
    id uuid NOT NULL PRIMARY KEY,
    name character varying(50) NOT NULL,
    description character varying(200) NOT NULL,
    type character varying(10) NOT NULL,
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
    name character varying(500) NOT NULL,
    highlight character varying(50) ,
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
    content character varying(500) NOT NULL,
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
