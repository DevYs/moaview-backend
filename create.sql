CREATE TABLE IF NOT EXISTS contents_type (
    contents_type_no INTEGER PRIMARY KEY AUTOINCREMENT,
    contents_type_name TEXT NOT NULL,
    sequence INTEGER NOT NULL DEFAULT 9999
);

CREATE TABLE IF NOT EXISTS site (
    site_no INTEGER PRIMARY KEY AUTOINCREMENT,
    site_name TEXT NOT NULL,
    site_url TEXT NOT NULL
);
CREATE INDEX IF NOT EXISTS site_name_idx ON site(site_name);

CREATE TABLE IF NOT EXISTS target_contents (
    target_contents_no INTEGER PRIMARY KEY AUTOINCREMENT,
    contents_type_no INTEGER NOT NULL,
    site_no INTEGER NOT NULL,
    target_contents_name TEXT NOT NULL,
    target_contents_url TEXT NOT NULL,
    target_contents_css_selector TEXT NOT NULL,
    title_css_selector NOT NULL DEFAULT 'meta[property=og:title]',
    title_attr NOT NULL DEFAULT 'content',
    description_css_selector NOT NULL DEFAULT 'meta[property=og:description]',
    description_attr NOT NULL DEFAULT 'content',
    image_css_selector NOT NULL DEFAULT 'meta[property=og:image]',
    image_attr NOT NULL DEFAULT 'content',
    url_css_selector NOT NULL DEFAULT 'meta[property=og:url]',
    url_attr NOT NULL DEFAULT 'content',
    response_code TEXT NOT NULL DEFAULT '200',
	response_message TEXT NOT NULL DEFAULT '200',
	FOREIGN KEY(contents_type_no) REFERENCES contents_type(contents_type_no),
    FOREIGN KEY(site_no) REFERENCES site(site_no)
);
CREATE INDEX IF NOT EXISTS target_contents_name_idx ON target_contents(target_contents_name);

CREATE TABLE IF NOT EXISTS contents (
	contents_no INTEGER PRIMARY KEY AUTOINCREMENT,
	target_contents_no INTEGER NOT NULL,
	title TEXT NOT NULL,
	url TEXT NOT NULL,
	description TEXT NOT NULL,
	thumbnail_url TEXT NOT NULL,
	reg_date NUMERIC NOT NULL,
	FOREIGN KEY(target_contents_no) REFERENCES target_contents(target_contents_no)
);
CREATE INDEX IF NOT EXISTS contents_target_contents_no_idx ON contents(target_contents_no);
CREATE INDEX IF NOT EXISTS contents_title_idx ON contents(title);
CREATE UNIQUE INDEX IF NOT EXISTS contents_url_uidx ON contents(url);
