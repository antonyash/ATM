--
-- PostgreSQL database dump
--

-- Dumped from database version 11.2
-- Dumped by pg_dump version 11.2

-- Started on 2019-06-04 00:22:11

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2312 (class 1262 OID 24576)
-- Name: AtmProject; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "AtmProject" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251';


ALTER DATABASE "AtmProject" OWNER TO postgres;

\connect "AtmProject"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2285 (class 0 OID 24761)
-- Dependencies: 198
-- Data for Name: atm; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.atm (id, bankid) VALUES (431, 431);
INSERT INTO public.atm (id, bankid) VALUES (432, 432);


--
-- TOC entry 2287 (class 0 OID 24769)
-- Dependencies: 200
-- Data for Name: bank; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.bank (id, name, encoderid) VALUES (431, 'Sberbank', 431);
INSERT INTO public.bank (id, name, encoderid) VALUES (432, 'SarBank', 432);


--
-- TOC entry 2289 (class 0 OID 24777)
-- Dependencies: 202
-- Data for Name: bankaccount; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.bankaccount (number, balance, departmentid) VALUES (1085, 1150, 415);
INSERT INTO public.bankaccount (number, balance, departmentid) VALUES (1083, 30000000, 415);
INSERT INTO public.bankaccount (number, balance, departmentid) VALUES (1082, 3000, 415);
INSERT INTO public.bankaccount (number, balance, departmentid) VALUES (1081, 2992, 415);
INSERT INTO public.bankaccount (number, balance, departmentid) VALUES (1084, 0, 415);
INSERT INTO public.bankaccount (number, balance, departmentid) VALUES (1087, 0, 416);
INSERT INTO public.bankaccount (number, balance, departmentid) VALUES (1086, 0, 416);


--
-- TOC entry 2290 (class 0 OID 24783)
-- Dependencies: 203
-- Data for Name: card; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.card (number, cardholdrname, cvv, dailypoplimit, expdate, monthlypoplimit, paymentsystemid) VALUES ('4431415000000018', 'AntonMedvedev', 458, 40000, '2022-05-31 21:10:05.051', 250000, 1);
INSERT INTO public.card (number, cardholdrname, cvv, dailypoplimit, expdate, monthlypoplimit, paymentsystemid) VALUES ('4431415500000013', 'ArinaOskina', 951, 40000, '2022-05-31 21:10:05.069', 250000, 1);
INSERT INTO public.card (number, cardholdrname, cvv, dailypoplimit, expdate, monthlypoplimit, paymentsystemid) VALUES ('4431415550000013', 'BillGates', 967, 40000, '2022-05-31 21:10:05.085', 250000, 1);
INSERT INTO public.card (number, cardholdrname, cvv, dailypoplimit, expdate, monthlypoplimit, paymentsystemid) VALUES ('5431415555000016', 'DimaKavtrov', 270, 40000, '2022-06-02 19:14:34.922', 250000, 2);
INSERT INTO public.card (number, cardholdrname, cvv, dailypoplimit, expdate, monthlypoplimit, paymentsystemid) VALUES ('4432416000000015', 'IlyaKriushenkov', 249, 40000, '2022-06-02 19:35:01.022', 250000, 1);
INSERT INTO public.card (number, cardholdrname, cvv, dailypoplimit, expdate, monthlypoplimit, paymentsystemid) VALUES ('4432416600000019', 'AntonNiki', 519, 40000, '2022-06-03 23:54:36.392', 250000, 1);


--
-- TOC entry 2292 (class 0 OID 24793)
-- Dependencies: 205
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.client (id, name, surname) VALUES (800, 'Bill', 'Gates');
INSERT INTO public.client (id, name, surname) VALUES (799, 'Arina', 'Oskina');
INSERT INTO public.client (id, name, surname) VALUES (798, 'Anton', 'Medvedev');
INSERT INTO public.client (id, name, surname) VALUES (801, 'Dima', 'Kavtrov');
INSERT INTO public.client (id, name, surname) VALUES (803, 'Anton', 'Niki');
INSERT INTO public.client (id, name, surname) VALUES (802, 'Ilya', 'Kriushenkov');


--
-- TOC entry 2293 (class 0 OID 24802)
-- Dependencies: 206
-- Data for Name: clientbankaccount; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.clientbankaccount (pvv, number, cardnumber, clientid) VALUES (3332386, 1085, '5431415555000016', 801);
INSERT INTO public.clientbankaccount (pvv, number, cardnumber, clientid) VALUES (337476, 1083, '4431415550000013', 800);
INSERT INTO public.clientbankaccount (pvv, number, cardnumber, clientid) VALUES (337476, 1082, '4431415500000013', 799);
INSERT INTO public.clientbankaccount (pvv, number, cardnumber, clientid) VALUES (99072, 1081, '4431415000000018', 798);
INSERT INTO public.clientbankaccount (pvv, number, cardnumber, clientid) VALUES (7631, 1087, '4432416600000019', 803);
INSERT INTO public.clientbankaccount (pvv, number, cardnumber, clientid) VALUES (55500273, 1086, '4432416000000015', 802);


--
-- TOC entry 2295 (class 0 OID 24809)
-- Dependencies: 208
-- Data for Name: company; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.company (id, name) VALUES (285, 'ETU');


--
-- TOC entry 2296 (class 0 OID 24815)
-- Dependencies: 209
-- Data for Name: companybankaccount; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.companybankaccount (number, companyid) VALUES (1084, 285);


--
-- TOC entry 2298 (class 0 OID 24822)
-- Dependencies: 211
-- Data for Name: department; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.department (id, region, bankid) VALUES (416, 'Moscow', 432);
INSERT INTO public.department (id, region, bankid) VALUES (415, 'Moscow', 431);


--
-- TOC entry 2300 (class 0 OID 24830)
-- Dependencies: 213
-- Data for Name: encoder; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.encoder (id, encodevalue) VALUES (431, 3);
INSERT INTO public.encoder (id, encodevalue) VALUES (432, 6);


--
-- TOC entry 2301 (class 0 OID 24836)
-- Dependencies: 214
-- Data for Name: mobile; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.mobile (number, balance, clientid) VALUES ('79217769813', 0, 798);
INSERT INTO public.mobile (number, balance, clientid) VALUES ('79766661337', 0, 799);
INSERT INTO public.mobile (number, balance, clientid) VALUES ('79777771137', 0, 800);
INSERT INTO public.mobile (number, balance, clientid) VALUES ('79178887653', 0, 801);
INSERT INTO public.mobile (number, balance, clientid) VALUES ('79059099987', 0, 802);
INSERT INTO public.mobile (number, balance, clientid) VALUES ('79096663636', 0, 803);


--
-- TOC entry 2303 (class 0 OID 24843)
-- Dependencies: 216
-- Data for Name: moneycase; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.moneycase (id, capacity, occupancy, billtype, atmid) VALUES (2983, 2500, 2500, 0, 431);
INSERT INTO public.moneycase (id, capacity, occupancy, billtype, atmid) VALUES (2984, 2500, 2500, 1, 431);
INSERT INTO public.moneycase (id, capacity, occupancy, billtype, atmid) VALUES (2985, 2500, 2500, 2, 431);
INSERT INTO public.moneycase (id, capacity, occupancy, billtype, atmid) VALUES (2986, 2500, 2500, 3, 431);
INSERT INTO public.moneycase (id, capacity, occupancy, billtype, atmid) VALUES (2987, 2500, 2500, 4, 431);
INSERT INTO public.moneycase (id, capacity, occupancy, billtype, atmid) VALUES (2988, 2500, 2500, 5, 431);
INSERT INTO public.moneycase (id, capacity, occupancy, billtype, atmid) VALUES (2989, 2500, 0, 6, 431);
INSERT INTO public.moneycase (id, capacity, occupancy, billtype, atmid) VALUES (2990, 2500, 2500, 0, 432);
INSERT INTO public.moneycase (id, capacity, occupancy, billtype, atmid) VALUES (2991, 2500, 2500, 1, 432);
INSERT INTO public.moneycase (id, capacity, occupancy, billtype, atmid) VALUES (2992, 2500, 2500, 2, 432);
INSERT INTO public.moneycase (id, capacity, occupancy, billtype, atmid) VALUES (2993, 2500, 2500, 3, 432);
INSERT INTO public.moneycase (id, capacity, occupancy, billtype, atmid) VALUES (2994, 2500, 2500, 4, 432);
INSERT INTO public.moneycase (id, capacity, occupancy, billtype, atmid) VALUES (2995, 2500, 2500, 5, 432);
INSERT INTO public.moneycase (id, capacity, occupancy, billtype, atmid) VALUES (2996, 2500, 0, 6, 432);


--
-- TOC entry 2305 (class 0 OID 24851)
-- Dependencies: 218
-- Data for Name: paymentsystem; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.paymentsystem (id, name, startwith) VALUES (1, 'MasterCard', 4);
INSERT INTO public.paymentsystem (id, name, startwith) VALUES (2, 'Visa', 5);
INSERT INTO public.paymentsystem (id, name, startwith) VALUES (3, 'Mir', 2);


--
-- TOC entry 2306 (class 0 OID 24857)
-- Dependencies: 219
-- Data for Name: transaction; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.transaction (id, comission, date, value, fromid, toid) VALUES (65, 50, '2019-06-03 00:28:55.563', 1000, 1081, 1085);
INSERT INTO public.transaction (id, comission, date, value, fromid, toid) VALUES (63, 7, '2019-06-02 21:40:13.248', 150, 1081, 1085);
INSERT INTO public.transaction (id, comission, date, value, fromid, toid) VALUES (64, 500, '2019-06-03 00:25:50.005', 10000, 1081, 1085);


--
-- TOC entry 2322 (class 0 OID 0)
-- Dependencies: 197
-- Name: atm_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.atm_id_seq', 432, true);


--
-- TOC entry 2323 (class 0 OID 0)
-- Dependencies: 199
-- Name: bank_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.bank_id_seq', 432, true);


--
-- TOC entry 2324 (class 0 OID 0)
-- Dependencies: 201
-- Name: bankaccount_number_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.bankaccount_number_seq', 1087, true);


--
-- TOC entry 2325 (class 0 OID 0)
-- Dependencies: 204
-- Name: client_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.client_id_seq', 803, true);


--
-- TOC entry 2326 (class 0 OID 0)
-- Dependencies: 207
-- Name: company_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.company_id_seq', 285, true);


--
-- TOC entry 2327 (class 0 OID 0)
-- Dependencies: 210
-- Name: department_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.department_id_seq', 416, true);


--
-- TOC entry 2328 (class 0 OID 0)
-- Dependencies: 212
-- Name: encoder_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.encoder_id_seq', 432, true);


--
-- TOC entry 2329 (class 0 OID 0)
-- Dependencies: 196
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 65, true);


--
-- TOC entry 2330 (class 0 OID 0)
-- Dependencies: 215
-- Name: moneycase_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.moneycase_id_seq', 2996, true);


--
-- TOC entry 2331 (class 0 OID 0)
-- Dependencies: 217
-- Name: paymentsystem_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.paymentsystem_id_seq', 3, true);


-- Completed on 2019-06-04 00:22:12

--
-- PostgreSQL database dump complete
--

