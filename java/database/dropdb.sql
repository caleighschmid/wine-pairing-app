-- **************************************************************
-- This script destroys the database and associated users
-- **************************************************************

-- The following line terminates any active connections to the database so that it can be destroyed
SELECT pg_terminate_backend(pid)
FROM pg_stat_activity
WHERE datname = 'wine_app';

DROP DATABASE wine_app;

DROP USER wine_app_owner;
DROP USER wine_app_appuser;
