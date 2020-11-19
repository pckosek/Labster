DROP TABLE IF EXISTS ratings;
CREATE TABLE ratings(id INT, player_name VARCHAR(100), upvotes INT, downvotes INT, PRIMARY KEY(id));
INSERT INTO ratings(id, player_name, upvotes, downvotes) VALUE (0, 'Josh Allen', 10, 1);
INSERT INTO ratings(id, player_name, upvotes, downvotes) VALUE (1, 'Tom Brady', 3, 12);
INSERT INTO ratings(id, player_name, upvotes, downvotes) VALUE (2, 'Patrick Mahomes', 9, 3);
INSERT INTO ratings(id, player_name, upvotes, downvotes) VALUE (3, 'Tua Tagovailoa', 5, 5);