
import resets from '../../_resets.module.css';
import Rectangle11 from '../Rectangle11/Rectangle11';
import classes from './ItemButton1.module.css';
import React from 'react'

/* @figmaId 46:1900 */
const ItemButton1 = (props) => {
  return (
    <div className={`${resets.storybrainResets} ${props.classes?.root || ''} ${props.className || ''} ${classes.root}`}>
      <Rectangle11
        text={{
          text: <div className={classes.text}>Thêm</div>,
        }}
      />
      <Rectangle11
        text={{
          text: <div className={classes.text2}>Sửa</div>,
        }}
      />
      <Rectangle11
        text={{
          text: <div className={classes.text3}>Xóa</div>,
        }}
      />
    </div>
  );
};

export default ItemButton1