package com.company.maps.view.location;

import com.company.maps.entity.Location;
import com.company.maps.view.main.MainView;
import com.vaadin.flow.data.selection.SelectionEvent;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.mapsflowui.component.GeoMap;
import io.jmix.mapsflowui.component.model.FitOptions;
import io.jmix.mapsflowui.component.model.layer.VectorLayer;
import io.jmix.mapsflowui.component.model.source.DataVectorSource;
import io.jmix.mapsflowui.kit.component.model.Extent;
import io.jmix.mapsflowui.kit.component.model.Padding;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateArrays;
import org.locationtech.jts.geom.Envelope;

import java.util.Optional;

@Route(value = "locations", layout = MainView.class)
@ViewController("Location.list")
@ViewDescriptor("location-list-view.xml")
@LookupComponent("locationsDataGrid")
@DialogMode(width = "64em")
public class LocationListView extends StandardListView<Location> {

    @ViewComponent
    private InstanceContainer<Location> locationDc;

    @ViewComponent
    private GeoMap map;
    @ViewComponent("map.buildingLayer")
    private VectorLayer mapBuildingLayer;
    @ViewComponent
    private CollectionContainer<Location> locationsDc;
    @ViewComponent("map.buildingLayer.buildingSource")
    private DataVectorSource<Location> buildingSource;
    @ViewComponent("map.buildingAreaLayer.buildingAreaSource")
    private DataVectorSource<Location> buildingAreaSource;
    @ViewComponent("map.pathToBuildingLayer.pathToBuildingSource")
    private DataVectorSource<Location> pathToBuildingSource;
    @ViewComponent("map.buildingEntranceLayer.buildingEntranceSource")
    private DataVectorSource<Location> buildingEntranceSource;

    @Subscribe
    public void onInit(final InitEvent event) {
        buildingSource.setStyleProvider(LocationStyles::getBuildingStyle);
        buildingAreaSource.setStyleProvider(LocationStyles::getBuildingAreaStyle);
        pathToBuildingSource.setStyleProvider(LocationStyles::getPathToBuildingStyle);
        buildingEntranceSource.setStyleProvider(LocationStyles::getBuildingEntranceStyle);
    }

    @Subscribe(id = "locationsDc", target = Target.DATA_CONTAINER)
    public void onLocationsDcItemChange(final InstanceContainer.ItemChangeEvent<Location> event) {
        locationDc.setItem(event.getItem());
    }

    @Subscribe("locationsDataGrid")
    public void onLocationsDataGridSelection(final SelectionEvent<DataGrid<Location>, Location> event) {
        Optional<Location> optionalLocation = event.getFirstSelectedItem();
        if (optionalLocation.isPresent()) {
            mapBuildingLayer.setVisible(false);

            Location location = optionalLocation.get();
            map.fit(new FitOptions(location.getBuilding()).withMaxZoom(19d));

        } else {
            mapBuildingLayer.setVisible(true);
            map.fit(new FitOptions(getAllVisibleLocationsExtent())
                    .withPadding(new Padding(20, 20, 20, 20))
                    .withMaxZoom(5d));
        }
    }

    private Extent getAllVisibleLocationsExtent() {
        Coordinate[] coordinates = locationsDc.getItems().stream()
                .map(loc -> loc.getBuilding().getCoordinate())
                .toArray(Coordinate[]::new);

        Envelope envelope = CoordinateArrays.envelope(coordinates);
        return new Extent(envelope.getMinX(), envelope.getMinY(), envelope.getMaxX(), envelope.getMaxY());
    }
}