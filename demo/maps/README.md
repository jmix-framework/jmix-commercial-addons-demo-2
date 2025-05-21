# Jmix Maps Demo

## Overview

The application's data model includes the [Location](src/main/java/com/company/maps/entity/Location.java) entity and [LocationType](src/main/java/com/company/maps/entity/LocationType.java) enumeration.

The `Location` entity describes a building that has an office or coworking space, and it includes the following attributes:
- `building` is a `Point` that specifies the location of the building.
- `buildingArea` is a `Polygon` that specifies the area of the building.
- `buildingEntrance` is a `Point` that specifies the entrance to the building.
- `pathToBuilding` is a `LineString` that specifies the path to the building from the nearest bus stop.

[LocationListView](src/main/java/com/company/maps/view/location/LocationListView.java) with [location-list-view.xml](src/main/resources/com/company/maps/view/location/location-list-view.xml) descriptor displays the locations in a data grid and on a map. The view is available via the *Application → Locations* menu item. 

[LocationDetailView](src/main/java/com/company/maps/view/location/LocationDetailView.java) is used to edit a location.

[LocationStyles](src/main/java/com/company/maps/view/location/LocationStyles.java) class provides map styles used both in the list and detail views.

## Demo Scenario

1. Run the application and go to <http://localhost:8104> in your browser.
2. Log in as `admin` with password `admin`.
3. Open the *Application → Locations* view. You will see the list of all locations and a map zoomed out to display all of them. 
4. Click on a location in the list. The map will zoom in to the selected location and show its area, entrance and path to the building.
5. Click again on the same row to deselect the location. The map will zoom out to show all locations.
6. Click *Create*. The location detail view will open to create a new location.
7. Click on the map to select the location's `building` attribute. 
8. Other attributes should be entered manually. 

## See Also

- [Maps Documentation](https://docs.jmix.io/jmix/maps/index.html)
- [Maps on Marketplace](https://www.jmix.io/marketplace/maps/)
