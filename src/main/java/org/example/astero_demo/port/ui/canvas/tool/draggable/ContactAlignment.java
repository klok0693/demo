package org.example.astero_demo.port.ui.canvas.tool.draggable;

import org.example.astero_demo.port.ui.canvas.tool.ShapeSelectionTool;

import static org.example.astero_demo.port.ui.UIConsrants.MINIMAL_SIDE_SIZE;

public enum ContactAlignment {
    TOP_LEFT {
        @Override
        public void updateContact(
                final ContactPoint contact,
                final double x,
                final double y,
                final double width,
                final double height) {

            contact.setValues(x, y, width, height);
        }

        /**
         * Adjust x, y, width, and height relative to mouseX and mouseY
         */
        @Override
        public void updateParentTool(final ShapeSelectionTool parentTool, final double mouseX, final double mouseY) {
            final double parentX = parentTool.getX();
            final double parentY = parentTool.getY();
            final double parentWidth = parentTool.getWidth();
            final double parentHeight = parentTool.getHeight();

            final double newX = Math.min(mouseX, parentX + parentWidth - MINIMAL_SIDE_SIZE);
            final double newY = Math.min(mouseY, parentY + parentHeight - MINIMAL_SIDE_SIZE);
            final double newWidth = parentWidth - (newX - parentX);
            final double newHeight = parentHeight - (newY - parentY);
            parentTool.update(newX, newY, newWidth, newHeight);
        }
    },
    TOP_CENTER {
        @Override
        public void updateContact(
                final ContactPoint contact,
                final double x,
                final double y,
                final double width,
                final double height) {

            final double centerX = x + (width / 2);
            contact.setValues(centerX, y, width, height);
        }

        /**
         * Only adjust y and height relative to mouseX and mouseY
         */
        @Override
        public void updateParentTool(final ShapeSelectionTool parentTool, final double mouseX, final double mouseY) {
            final double parentX = parentTool.getX();
            final double parentY = parentTool.getY();
            final double parentWidth = parentTool.getWidth();
            final double parentHeight = parentTool.getHeight();

            final double newY = Math.min(mouseY, parentY + parentHeight - MINIMAL_SIDE_SIZE);
            final double newHeight = parentHeight - (newY - parentY);
            parentTool.update(parentX, newY, parentWidth, newHeight);
        }
    },
    TOP_RIGHT {
        @Override
        public void updateContact(
                final ContactPoint contact,
                final double x,
                final double y,
                final double width,
                final double height) {

            final double rightX = x + width;
            contact.setValues(rightX, y, width, height);
        }

        /**
         * Adjust width, y, and height relative to mouseX and mouseY
         */
        @Override
        public void updateParentTool(final ShapeSelectionTool parentTool, final double mouseX, final double mouseY) {
            final double parentX = parentTool.getX();
            final double parentY = parentTool.getY();
            final double parentHeight = parentTool.getHeight();

            final double newWidth = mouseX - parentX;
            final double newY = Math.min(mouseY, parentY + parentHeight - MINIMAL_SIDE_SIZE);
            final double newHeight = parentHeight - (newY - parentY);
            parentTool.update(parentX, newY, newWidth, newHeight);
        }
    },
    CENTER_RIGHT {
        @Override
        public void updateContact(
                final ContactPoint contact,
                final double x,
                final double y,
                final double width,
                final double height) {

            final double rightX = x + width;
            final double centerY = y + (height / 2);
            contact.setValues(rightX, centerY, width, height);
        }

        /**
         * Adjust width only relative to mouseX and mouseY
         */
        @Override
        public void updateParentTool(final ShapeSelectionTool parentTool, final double mouseX, final double mouseY) {
            final double parentX = parentTool.getX();
            final double parentY = parentTool.getY();
            final double parentHeight = parentTool.getHeight();

            final double newWidth = mouseX - parentX;
            parentTool.update(parentX, parentY, newWidth, parentHeight);
        }
    },
    BOTTOM_RIGHT {
        @Override
        public void updateContact(
                final ContactPoint contact,
                final double x,
                final double y,
                final double width,
                final double height) {

            final double rightX = x + width;
            final double bottomY = y + height;
            contact.setValues(rightX, bottomY, width, height);
        }

        /**
         * Adjust width and height relative to mouseX and mouseY
         */
        @Override
        public void updateParentTool(final ShapeSelectionTool parentTool, final double mouseX, final double mouseY) {
            final double parentX = parentTool.getX();
            final double parentY = parentTool.getY();
            final double newWidth = mouseX - parentX;
            final double newHeight = mouseY - parentY;
            parentTool.update(parentX, parentY, newWidth, newHeight);
        }
    },
    BOTTOM_CENTER {
        @Override
        public void updateContact(
                final ContactPoint contact,
                final double x,
                final double y,
                final double width,
                final double height) {

            final double centerX = x + (width / 2);
            final double bottomY = y + height;
            contact.setValues(centerX, bottomY, width, height);
        }

        /**
         * Adjust height only relative to mouseX and mouseY
         */
        @Override
        public void updateParentTool(final ShapeSelectionTool parentTool, final double mouseX, final double mouseY) {
            final double parentX = parentTool.getX();
            final double parentY = parentTool.getY();
            final double parentWidth = parentTool.getWidth();
            final double newHeight = mouseY - parentY;
            parentTool.update(parentX, parentY, parentWidth, newHeight);
        }
    },
    BOTTOM_LEFT {
        @Override
        public void updateContact(
                final ContactPoint contact,
                final double x,
                final double y,
                final double width,
                final double height) {

            final double bottomY = y + height;
            contact.setValues(x, bottomY, width, height);
        }

        /**
         * Adjust x, width, and height relative to mouseX and mouseY
         */
        @Override
        public void updateParentTool(final ShapeSelectionTool parentTool, final double mouseX, final double mouseY) {
            final double parentX = parentTool.getX();
            final double parentY = parentTool.getY();
            final double parentWidth = parentTool.getWidth();

            final double newX = Math.min(mouseX, parentX + parentWidth - MINIMAL_SIDE_SIZE);
            final double newY = parentY;
            final double newWidth = (parentX + parentWidth) - newX;
            final double newHeight = mouseY - parentY;
            parentTool.update(newX, newY, newWidth, newHeight);
        }
    },
    CENTER_LEFT {
        @Override
        public void updateContact(
                final ContactPoint contact,
                final double x,
                final double y,
                final double width,
                final double height) {

            final double centerY = y + (height / 2);
            contact.setValues(x, centerY, width, height);
        }

        /**
         * Adjust x and width relative to mouseX and mouseY
         */
        @Override
        public void updateParentTool(final ShapeSelectionTool parentTool, final double mouseX, final double mouseY) {
            final double parentX = parentTool.getX();
            final double parentY = parentTool.getY();
            final double parentWidth = parentTool.getWidth();
            final double parentHeight = parentTool.getHeight();

            final double newX = Math.min(mouseX, parentX + parentWidth - MINIMAL_SIDE_SIZE);
            final double newWidth = (parentX + parentWidth) - newX;
            parentTool.update(newX, parentY, newWidth, parentHeight);
        }
    };

    public abstract void updateContact(ContactPoint contact, double x, double y, double width, double height);

    public abstract void updateParentTool(ShapeSelectionTool parentTool, double x, double y);
}
