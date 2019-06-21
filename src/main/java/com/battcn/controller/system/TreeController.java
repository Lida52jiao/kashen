package com.battcn.controller.system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.entity.Tree;
import com.battcn.entity.UserEntity;
import com.battcn.service.system.TreeService;
import com.battcn.util.UserEntityUtil;

@Controller
@RequestMapping("Tree")
public class TreeController {
	
	@Autowired
	private TreeService treeService;
	
	@RequestMapping("findsAgent")
	@ResponseBody
	public List<Tree> findsAgent() {
		UserEntity k=UserEntityUtil.getUserFromSession();
		List<Tree> lists=treeService.queryList();
		Tree tree=new Tree();
		tree.setMerId(k.getMerId());
		Tree t=treeService.findByObject(tree);
		List<Tree> list=new ArrayList<Tree>();
		List<Tree> agentList=get(list,t.getOneMerId());
		if(!k.getMerId().startsWith("T")){
			lists.removeAll(agentList);
			lists.add(t);
		}
		return parseMenus(lists,k);
	}
	
	private List<Tree> get(List<Tree> list, String oneMerId) {
		if("".equals(oneMerId) || null == oneMerId){
			return list;
		}
		if(!"".equals(oneMerId) && null != oneMerId){
			Tree tree=new Tree();
			tree.setMerId(oneMerId);
			Tree n=treeService.findByObject(tree);
			Tree t=new Tree();
			t.setOneMerId(oneMerId);
			List<Tree> agentList=treeService.queryObjectForList(t);
			list.add(n);
			list.addAll(agentList);
			get(list,n.getOneMerId());
		}
		return list;	
	}
	
	/**
	 * 将没有树状结构的Menus集合转化成有树状结构的集合
	 * 返回的集合中的元素,是根对象.有子节点集合children
	 * @param resource
	 * @param k 
	 * @return
	 */
	private List<Tree> parseMenus(List<Tree> resource, UserEntity k){
		
		// 结果对象
		List<Tree> result = new ArrayList<Tree>();
		if(k.getMerId().startsWith("T")){
			// 传入的集合中的每个元素是一个Menus对象. 没有树状结构.
			// 第一次循环.将当前元素作为子节点循环迭代. 查询当前元素有没有父节点. 没有父节点代表是根节点
			for(Tree child : resource){
				// 父节点ID
				String oneMerId = child.getOneMerId();
				if("".equals(oneMerId) || null == oneMerId){
					// 根节点, 没有父节点.
					result.add(child);
					continue;
				}
				
				// 查找当前节点的父节点.
				for(Tree parent : resource){
					if(oneMerId.equals(parent.getMerId())){
						// 当前parent对象是child对象的父节点.
						parent.getChildren().add(child);
						// 一个子节点只有唯一的一个父节点
						break;
					}
				}
				
			}
			return result;
		}
		Tree tree=new Tree();
		tree.setMerId(k.getMerId());
		Tree n=treeService.findByObject(tree);
		// 传入的集合中的每个元素是一个Menus对象. 没有树状结构.
		// 第一次循环.将当前元素作为子节点循环迭代. 查询当前元素有没有父节点. 没有父节点代表是根节点
		for(Tree child : resource){
			// 父节点ID
			String oneMerId = child.getOneMerId();
			if(oneMerId.equals(n.getOneMerId())){
				// 根节点, 没有父节点.
				result.add(child);
				continue;
			}
			
			// 查找当前节点的父节点.
			for(Tree parent : resource){
				if(oneMerId.equals(parent.getMerId())){
					// 当前parent对象是child对象的父节点.
					parent.getChildren().add(child);
					// 一个子节点只有唯一的一个父节点
					break;
				}
			}
			
		}
		return result;
	}
	
}
