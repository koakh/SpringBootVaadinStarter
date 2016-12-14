package com.koakh.springbootvaadinstarter;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.annotations.Theme;
import com.vaadin.ui.renderers.ClickableRenderer;
import org.vaadin.gridutil.cell.GridCellFilter;
import org.vaadin.gridutil.renderer.EditButtonValueRenderer;
import org.vaadin.gridutil.renderer.EditDeleteButtonValueRenderer;

import java.util.Arrays;

@SpringUI
@Theme("SpringBootVaadinStarter")
public class MainUI extends UI {

  @Override
  protected void init(VaadinRequest request) {

    VerticalLayout verticalLayout = new VerticalLayout();
    verticalLayout.addComponent(getGrid());

    setContent(verticalLayout);
  }

  private Grid getGrid() {

    /*
    Grid grid = new Grid();
    grid.setSizeFull();

    grid.addColumn("index", Integer.class)
        .setRenderer(new NumberRenderer("%02d")).setHeaderCaption("##")
        .setExpandRatio(0);

    grid.addColumn("name", String.class)
        .setRenderer(new BoldLastNameRenderer()).setExpandRatio(2);

    grid.addColumn("progress", Double.class)
        .setRenderer(new ProgressBarRenderer()).setExpandRatio(2);

    grid.addColumn("edit", FontIcon.class)
        .setRenderer(new FontIconRenderer(new ClickableRenderer.RendererClickListener() {
          @Override
          public void click(ClickableRenderer.RendererClickEvent e) {
            Notification.show("Editing item " + e.getItemId());
          }
        }));

    grid.addColumn("delete", FontIcon.class)
        .setRenderer(new FontIconRenderer(new ClickableRenderer.RendererClickListener() {
          @Override
          public void click(ClickableRenderer.RendererClickEvent e) {
            Notification.show("Deleted item " + e.getItemId());
          }
        }));

    grid.getDefaultHeaderRow().join("edit", "delete").setText("Tools");

    return grid;
    */

    /*
    Grid grid = new Grid();

    grid.addColumn("name", String.class).setHeaderCaption("Name");
    grid.addColumn("icon", String.class)
        .setHeaderCaption("icon")
        .setRenderer(new FontIconRenderer(e -> Notification.show(e.toString())));

    grid.getDefaultHeaderRow().join("name", "icon").setText("Brand");

    grid.addRow("Android", FontAwesome.ANDROID.getHtml());
    grid.addRow("Ios", FontAwesome.APPLE.getHtml());
    grid.addRow("Who cares", FontAwesome.WINDOWS.getHtml());

    return grid;
    */

    // Create a grid
    Grid grid = new Grid();
    grid.setSizeFull();
    grid.setCaption("My Grid");

    // Define some columns
    grid.addColumn("id", Integer.class);
    grid.addColumn("name", String.class);
    grid.addColumn("born", Integer.class);
    grid.addColumn("gender", Gender.class);
    grid.addColumn("active", Boolean.class);
    grid.addColumn("action", Integer.class);

    // Add some data rows
    grid.addRow(1, "Nicolaus Copernicus", 1543, Gender.MALE, true, 10);
    grid.addRow(2, "Galileo Galilei", 1564, Gender.FEMALE, false, 20);
    grid.addRow(3, "Johannes Kepler", 1571, Gender.MALE, true, 30);

    // init filter this add's a HeaderRow to the given grid
    final GridCellFilter filter = new GridCellFilter(grid);
    filter.setNumberFilter("id");

    // set gender Combo with custom icons
    ComboBox genderCombo = filter.setComboBoxFilter("gender", Arrays.asList(Gender.MALE, Gender.FEMALE));
    genderCombo.setItemIcon(Gender.MALE, FontAwesome.MALE);
    genderCombo.setItemIcon(Gender.FEMALE, FontAwesome.FEMALE);

    // simple filters
    filter.setTextFilter("name", true, true);
    filter.setNumberFilter("born");
    //filter.setDateFilter("sex");
    filter.setBooleanFilter("active");

    grid.getColumn("id").setHeaderCaption("##");
    grid.getColumn("id").setRenderer(new EditDeleteButtonValueRenderer(new EditDeleteButtonValueRenderer.EditDeleteButtonClickListener() {

      @Override
      public void onEdit(final ClickableRenderer.RendererClickEvent event) {
        Notification.show(event.getItemId().toString() + " want's to get edited", Notification.Type.HUMANIZED_MESSAGE);
      }

      @Override
      public void onDelete(final com.vaadin.ui.renderers.ClickableRenderer.RendererClickEvent event) {
        Notification.show(event.getItemId().toString() + " want's to get delete", Notification.Type.WARNING_MESSAGE);
      }
    }));

    grid.getColumn("active").setRenderer(new EditButtonValueRenderer(
            new ClickableRenderer.RendererClickListener() {
              @Override
              public void click(final ClickableRenderer.RendererClickEvent event) {
                Notification.show(event.getItemId().toString() + " want's to get edited", Notification.Type.HUMANIZED_MESSAGE);
              }
            }
        )
    );

    grid.getColumn("action").setRenderer(new EditButtonValueRenderer(
            new ClickableRenderer.RendererClickListener() {
              @Override
              public void click(final ClickableRenderer.RendererClickEvent event) {
                Notification.show(event.getItemId().toString() + " want's to get edited", Notification.Type.HUMANIZED_MESSAGE);
              }
            }
        )
    );

    // first of all you need to set a custom style to the column
    grid.setCellStyleGenerator(new Grid.CellStyleGenerator() {
      @Override
      public String getStyle(final Grid.CellReference cellReference) {
        if (cellReference.getPropertyId().equals("active")) {
          return "link-icon-active";
        } else {
        if (cellReference.getPropertyId().equals("action")) {
          return "link-icon-action";
        } else {
          return null;
        }
      }
    }});

    return grid;
  }
}
