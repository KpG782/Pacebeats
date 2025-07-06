// src/services/appwrite.ts
import { Client, Account, Databases, ID } from 'appwrite'

// Initialize the Appwrite client using environment variables
const client = new Client()
    .setEndpoint(process.env.EXPO_PUBLIC_APPWRITE_ENDPOINT!)    // your Appwrite endpoint
    .setProject(process.env.EXPO_PUBLIC_APPWRITE_PROJECT_ID!)   // your Appwrite project ID

// Export auth and database services
export const account  = new Account(client)
export const database = new Databases(client)

// Export your IDs so you never have hard-coded strings in your code.
export const DATABASE_ID   = process.env.EXPO_PUBLIC_APPWRITE_DATABASE_ID!
export const COLLECTION_ID = process.env.EXPO_PUBLIC_APPWRITE_COLLECTION_ID!

/**
 * Create a new file record in the Appwrite database.
 * @param filename - The name of the file to store.
 */
export async function createFileRecord(filename: string, count: number = 1) {
    // The 'distance' collection requires 'name' and 'count' attributes
    return database.createDocument(
        DATABASE_ID,
        COLLECTION_ID,
        ID.unique(),
        { name: filename, count },   // map filename to 'name', and supply a count
    )
}



/**
 * Create a random distance record with a random name and count.
 */
export async function createRandomDistanceRecord() {
    const randomName = `run-${Math.random().toString(36).substring(2, 8)}`
    const randomCount = Math.floor(Math.random() * 100) + 1
    return database.createDocument(
        DATABASE_ID,
        COLLECTION_ID,
        ID.unique(),
        { name: randomName, count: randomCount }  // no permissions args
    )
}

export default client
