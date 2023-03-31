-- INCREASE & DECREASE LIKE
-- AFTER CREATE
CREATE FUNCTION increase_like_function()
    RETURNS TRIGGER AS
    $$
BEGIN
UPDATE post SET like_count = like_count+1 WHERE id = NEW.post_id;
RETURN NEW;
END;
$$

LANGUAGE 'plpgsql';

CREATE TRIGGER increase_like
    AFTER INSERT ON post_like
    FOR EACH ROW
    EXECUTE PROCEDURE increase_like_function();

-- AFTER DELETE
CREATE FUNCTION like_delete_function()
    RETURNS TRIGGER AS
    $$
BEGIN
UPDATE post SET like_count = like_count-1 WHERE id = OLD.post_id;
RETURN NEW;
END;
$$

LANGUAGE 'plpgsql';


CREATE TRIGGER delete_like
    AFTER DELETE ON post_like
    FOR EACH ROW
    EXECUTE PROCEDURE like_delete_function();