package com.company.maps.view.location;

import com.company.maps.entity.Location;
import com.company.maps.entity.LocationType;
import io.jmix.mapsflowui.kit.component.model.Padding;
import io.jmix.mapsflowui.kit.component.model.style.Fill;
import io.jmix.mapsflowui.kit.component.model.style.Style;
import io.jmix.mapsflowui.kit.component.model.style.image.Anchor;
import io.jmix.mapsflowui.kit.component.model.style.image.CircleStyle;
import io.jmix.mapsflowui.kit.component.model.style.image.IconOrigin;
import io.jmix.mapsflowui.kit.component.model.style.image.IconStyle;
import io.jmix.mapsflowui.kit.component.model.style.stroke.Stroke;
import io.jmix.mapsflowui.kit.component.model.style.text.TextStyle;

import java.util.List;

public class LocationStyles {

    public static Style getBuildingStyle(Location location) {
        return new Style()
                .withImage(new IconStyle()
                        .withScale(0.5)
                        .withAnchorOrigin(IconOrigin.BOTTOM_LEFT)
                        .withAnchor(new Anchor(0.49, 0.12))
                        .withSrc(location.getType() == LocationType.OFFICE
                                ? "icons/office-marker.png"
                                : "icons/coworking-marker.png"))
                .withText(new TextStyle()
                        .withBackgroundFill(new Fill("rgba(255, 255, 255, 0.6)"))
                        .withPadding(new Padding(5, 5, 5, 5))
                        .withOffsetY(15)
                        .withFont("bold 15px sans-serif")
                        .withText(location.getCity()));
    }

    public static Style getBuildingAreaStyle(Location location) {
        String fillColor = location.getType() == LocationType.COWORKING
                ? "rgba(52, 216, 0, 0.2)"
                : "rgba(1, 147, 154, 0.2)";
        String strokeColor = location.getType() == LocationType.COWORKING
                ? "#228D00"
                : "#123EAB";
        return new Style()
                .withFill(new Fill(fillColor))
                .withStroke(new Stroke()
                        .withWidth(2d)
                        .withColor(strokeColor));
    }

    public static Style getBuildingEntranceStyle(Location location) {
        return new Style()
                .withImage(new CircleStyle()
                        .withRadius(4)
                        .withFill(new Fill("#000000"))
                        .withStroke(new Stroke()
                                .withWidth(2d)
                                .withColor("#ffffff")));
    }

    public static Style getPathToBuildingStyle(Location location) {
        return new Style()
                .withStroke(new Stroke()
                        .withWidth(2d)
                        .withColor("#000000")
                        .withLineDash(List.of(0.2, 8d, 0.8d)));
    }
}
