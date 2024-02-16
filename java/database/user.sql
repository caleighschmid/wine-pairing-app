-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

CREATE USER wine_app_owner
WITH PASSWORD 'wineapp';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO wine_app_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO wine_app_owner;

CREATE USER wine_app_appuser
WITH PASSWORD 'wineapp';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO wine_app_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO wine_app_appuser;
