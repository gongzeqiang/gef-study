package com.qualityeclipse.genealogy.listener;

import org.eclipse.draw2d.*;
import org.eclipse.draw2d.geometry.*;

/**
 * A Draw2D mouse listener for dragging figures around the diagram. Listeners such as this
 * are useful for manipulating Draw2D diagrams, but are superseded by higher level GEF
 * functionality if the GEF framework is used.
 */
public class FigureMover implements MouseListener, MouseMotionListener
{
	private IFigure figure;
	private Point location;
	
	//�Ѵ����figure��Ϊ�������Ķ������FigureMover�������Ϊ������
	public FigureMover(IFigure figure) {
		this.figure=figure;
		figure.addMouseListener(this);
		figure.addMouseMotionListener(this);
	}
	//��������϶�
	@Override
	public void mouseDragged(MouseEvent me) {
		//У��λ�ú����ƶ���λ���Ƿ����
		if(location==null){
			return;
		}
		Point newLocation=me.getLocation();
		if(newLocation==null){
			return;
		}
		//����ƫ���������ƫ����Ϊ0���ͷ���
		Dimension offset=newLocation.getDifference(location);
		if(offset.width==0 && offset.height==0){
			return;
		}
		//�趨���϶��ĵ�ַΪ��ǰ��ַ
		location=newLocation;
		//��ȡ���¹������Ͳ��ֹ�����
		UpdateManager updateManager=figure.getUpdateManager();
		LayoutManager layoutManager=figure.getParent().getLayoutManager();
		//����һ����С�İ������ͼ�εľ���
		Rectangle bounds=figure.getBounds();
		updateManager.addDirtyRegion(figure.getParent(), bounds);
		bounds = bounds.getCopy().translate(offset.width, offset.height);
		layoutManager.setConstraint(figure, bounds);
		figure.translate(offset.width, offset.height);
		updateManager.addDirtyRegion(figure.getParent(), bounds);
		me.consume();
		
	}

	@Override
	public void mouseEntered(MouseEvent me) {
		
	}

	@Override
	public void mouseExited(MouseEvent me) {
		
	}

	@Override
	public void mouseHover(MouseEvent me) {
		
	}

	@Override
	public void mouseMoved(MouseEvent me) {
		
	}

	@Override
	public void mousePressed(MouseEvent me) {
		location = me.getLocation();
		me.consume();
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		if (location == null)
			return;
		location = null;
		me.consume();
	}

	@Override
	public void mouseDoubleClicked(MouseEvent me) {
		
	}
}