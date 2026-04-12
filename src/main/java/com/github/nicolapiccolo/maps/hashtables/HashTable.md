# Hash Table

## Quick recap about hash tables

A hash table is a data structure that maps keys to values, providing efficient lookup, insertion, and deletion operations.

The core idea is to use a **hash function** to convert a key into an integer index, which determines where the corresponding value is stored in an internal array of **buckets**.

Since different keys can produce the same index (a **collision**), each bucket holds a list of entries. This collision resolution strategy is known as **separate chaining**.

## How it works

The process of mapping a key to a bucket index involves two steps:

1. **Hash code** — the key is converted into an integer using a cyclic shift hash function, which combines bit shifting and character values to produce a well-distributed hash.

2. **Compression** — the hash code is compressed into a valid bucket index using a **MAD (Multiply-Add-Divide)** scheme: `((hash * multiplier + offset) % prime) % bucketArraySize`. The prime number and random parameters are chosen to minimize collisions.

## Operations

- **put(key, value)** — computes the bucket index, then either replaces an existing entry with the same key or inserts a new one. Time complexity is O(1) amortized.

- **get(key)** — computes the bucket index and searches the bucket for a matching key. Returns `null` if not found. Time complexity is O(1) amortized.

- **delete(key)** — computes the bucket index and removes the matching entry if present. Time complexity is O(1) amortized.

## Dynamic resizing

The hash table monitors its **load factor** (number of entries divided by number of buckets). When the load factor exceeds a threshold, the bucket array is doubled in size and all existing entries are rehashed into the new array. This keeps the average bucket length short and maintains O(1) performance.

Here follows the Java implementation of a hash table: [Java implementation](https://github.com/nicola-piccolo/data-structures-and-algorithms/blob/dev/src/main/java/com/github/nicolapiccolo/maps/hashtables/HashTable.java).
