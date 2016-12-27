开场步骤

1 设置场次信息，包括考试人员选择，考试时间设置，试卷策略选择等
    实际上，该步骤分为两个部门，考试信息设置，用户选择。信息保存可以设置在两个事务中。
2 生成试卷
3 场次信息预览，将场次信息全部展示，如果有问题可返回修改，如果放弃开场，将场次状态设置为取消，取消原因设置为未确认。

两个步骤在各自的事务中处理，可以分布进行。也可在考试之前再次重新设计试卷。


开场权限需要由部门，机构，岗位负责人设置，如果勾选以勾选为准，否则以本部门下属机构



可更换试卷设计草拟：
1 每场考试每人有两份试卷，状态都为可用，其中一份设置为当前试卷，另一份为备选。
2 考试中途允许更换一次试卷，更换完试卷后，所有记录不作为成绩，只做记录。一切以新试卷为主。剩余考试试卷为总答题时间减去第一份试题的时间。
3 第一份试卷答题15分钟之后不能进行试卷更换。






生成试卷方法：
//开场生成所有试卷。 scene中包含所有的场次信息，包含出题策略，考试人员等信息。
boolean generateAllPaper(Scene scene);

//适合为开场之前添加的考试用户出题，开场之后禁止添加用户
boolean generatePaperForUser(Scene scene, User user);

//禁止考试。此时需要把用户从场次关联表中的状态改为禁用
boolean disabledUserOfScene(Scene scene, User user);



//提交答案
boolean commit(Integer questionId, String answer);

//计算全场考试成绩
boolean computeAll(Scene scene);
//计算单人考试成绩
boolean computeOfUser(Scene scene, User user);
//试卷预览
Paper getPaper(Scene scene, User user);





