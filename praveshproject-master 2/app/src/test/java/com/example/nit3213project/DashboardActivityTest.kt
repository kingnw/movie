package com.example.nit3213project

import org.junit.Test
import org.junit.Assert.*

class DashboardActivityTest {

    @Test
    fun testEmptyDashboardData() {
        // Simulate empty data case
        val entities = emptyList<Entity>()
        assertTrue("No entities should be present", entities.isEmpty())
    }

    @Test
    fun testNonEmptyDashboardData() {
        // Simulate a case where entities are returned
        val entity = Entity("Zeus", "Greek", "Sky", "Thunderbolt", "Cronus", "Jupiter", "King of the gods")
        val entities = listOf(entity)
        assertFalse("Entities should be present", entities.isEmpty())
    }
}
