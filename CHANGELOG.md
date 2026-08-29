# Changelog

## [1.0.0] - 2026-08-29

### Features
- add PATCH status for orders/returns, fix duplicate-prefix paths, add checkout/customer-action/return endpoints
- add GET /api/orders/stats/by-store endpoint
- add JavaDoc, health aliases, and component tests

### Bug Fixes
- update OrderItemRepository and ReturnItemRepository ID types to UUID
- change OrderItem and ReturnItem IDs from String to UUID
- update OrderServiceTest mock to use UUID instead of String
- change entity IDs from String to UUID to fix PostgreSQL type mismatch
- walk full cause chain for EntityNotFoundException in global handler
- catch EntityNotFoundException instead of NoSuchElementException on status patch
- return 404 instead of 500 when patching non-existent order/return
- return 404 instead of 500 when order/return not found on status patch
- restrict stats/by-store to superadmin, unwrap EntityNotFoundException
- resolve merge conflict — keep getAnalytics and countActiveByStoreId
- commit missing OrderRepository query methods and OrderStatus.CONFIRMED enum value
- remove liquibase default-schema to allow fresh DB bootstrap
- update controller @RequestMapping paths to match gateway routes
- run create-schema always so it recreates if missing
- accept any checksum for idempotent create-schema changeset
- limit HikariCP pool to 2 connections (db-f1-micro max 25 total)
- disable Hibernate validation (Liquibase owns schema, uuid vs String mismatch)
- set liquibase-schema=public so schema is created before tracking tables
- add Cloud SQL postgres-socket-factory for Cloud Run connectivity

### Performance
- fix N+1 lazy-load on Order list endpoints

### Documentation
- add complete project documentation

### CI/Build
- retrigger prod deploy
- retrigger after db-g1-small upgrade
- trigger first dev build
- use separate GCP project IDs for dev (digi-carts-dev) and prod (digi-carts)